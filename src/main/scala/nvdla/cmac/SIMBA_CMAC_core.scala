

package nvdla

import chisel3._
import chisel3.experimental._
import chisel3.util._

class SOMNIA_CMAC_core(implicit val conf: somniaConfig) extends Module {
    val io = IO(new Bundle {
        //clock
        val somnia_clock = Flipped(new somnia_clock_if)
        val slcg_op_en = Input(UInt(conf.CMAC_SLCG_NUM.W))

        //odif
        val sc2mac_dat = Flipped(ValidIO(new csc2cmac_data_if))  /* data valid */
        val sc2mac_wt = Flipped(ValidIO(new csc2cmac_wt_if))  /* data valid */
        val mac2accu = ValidIO(new cmac2cacc_if) /* data valid */

        //reg
        val dp2reg_done = Output(Bool())

    })
//
//          ┌─┐       ┌─┐
//       ┌──┘ ┴───────┘ ┴──┐
//       │                 │
//       │       ───       │
//       │  ─┬┘       └┬─  │
//       │                 │
//       │       ─┴─       │
//       │                 │
//       └───┐         ┌───┘
//           │         │
//           │         │
//           │         │
//           │         └──────────────┐
//           │                        │
//           │                        ├─┐
//           │                        ┌─┘
//           │                        │
//           └─┐  ┐  ┌───────┬──┐  ┌──┘
//             │ ─┤ ─┤       │ ─┤ ─┤
//             └──┴──┘       └──┴──┘
    //==========================================================
    // interface with register config
    //==========================================================
    val somnia_op_gated_clk = Wire(Vec(conf.CMAC_ATOMK+3, Clock()))

    //==========================================================
    // input retiming logic
    //==========================================================
    val u_rt_in = Module(new SOMNIA_CMAC_CORE_rt_in(useRealClock = false))

    //u_rt_in.io.somnia_core_clk := somnia_op_gated_clk(conf.CMAC_ATOMK)
    u_rt_in.io.somnia_core_clk := clock
    u_rt_in.io.sc2mac_dat <> io.sc2mac_dat
    u_rt_in.io.sc2mac_wt <> io.sc2mac_wt

    //==========================================================
    // input shadow and active pipeline
    //==========================================================
    val u_active = Module(new SOMNIA_CMAC_CORE_active(useRealClock =false))

    //u_active.io.somnia_core_clk := somnia_op_gated_clk(conf.CMAC_ATOMK+1)
    u_active.io.somnia_core_clk := clock


    u_active.io.in_dat <> u_rt_in.io.in_dat
    u_active.io.in_dat_stripe_st := u_rt_in.io.in_dat.bits.pd(conf.PKT_nvdla_stripe_info_stripe_st_FIELD)                 //|< w
    u_active.io.in_dat_stripe_end := u_rt_in.io.in_dat.bits.pd(conf.PKT_nvdla_stripe_info_stripe_end_FIELD)               //|< w
    u_active.io.in_wt <> u_rt_in.io.in_wt

    //==========================================================
    // MAC CELLs
    //==========================================================
    val u_mac = Array.fill(conf.CMAC_ATOMK){Module(new SOMNIA_CMAC_CORE_mac(useRealClock = false))}
    val u_rt_out = Module(new SOMNIA_CMAC_CORE_rt_out(useRealClock = false))  // use seq

    for(i<- 0 to conf.CMAC_ATOMK-1){

        //u_mac(i).io.somnia_core_clk := somnia_op_gated_clk(i)
        u_mac(i).io.somnia_core_clk := clock

        u_mac(i).io.dat_actv <> u_active.io.dat_actv(i)
        u_mac(i).io.wt_actv <> u_active.io.wt_actv(i)

        u_rt_out.io.out.bits.mask(i) := u_mac(i).io.mac_out.valid
        u_rt_out.io.out.bits.data(i) := u_mac(i).io.mac_out.bits
    }

    //==========================================================
    // output retiming logic
    //==========================================================
    //u_rt_out.io.somnia_core_clk := somnia_op_gated_clk(conf.CMAC_ATOMK+2)
    //u_rt_out.io.out.valid := withClock(io.somnia_clock.somnia_core_clk){ShiftRegister(u_rt_in.io.in_dat.valid, conf.MAC_PD_LATENCY)}     //|< w
    //u_rt_out.io.out.bits.pd := withClock(io.somnia_clock.somnia_core_clk){ShiftRegister(u_rt_in.io.in_dat.bits.pd, conf.MAC_PD_LATENCY, u_rt_in.io.in_dat.valid)}     //|< w
     u_rt_out.io.somnia_core_clk := clock
     u_rt_out.io.out.valid := ShiftRegister(u_rt_in.io.in_dat.valid, conf.MAC_PD_LATENCY)

     u_rt_out.io.out.bits.pd := ShiftRegister(u_rt_in.io.in_dat.bits.pd, conf.MAC_PD_LATENCY)

    io.dp2reg_done := u_rt_out.io.dp2reg_done                   //|> o
    io.mac2accu <> u_rt_out.io.mac2accu         //|> o )
    //==========================================================
    // SLCG groups
    //==========================================================
    val u_slcg_op = Array.fill(conf.CMAC_ATOMK+3){Module(new SOMNIA_slcg(1, false))}

    for(i<- 0 to conf.CMAC_ATOMK+2){
        u_slcg_op(i).io.somnia_clock <> io.somnia_clock
        //u_slcg_op(i).io.somnia_clock.somnia_core_clk := clock
        //u_slcg_op(i).io.somnia_clock.dla_clk_ovr_on_sync := clock
        //u_slcg_op(i).io.somnia_clock.global_clk_ovr_on_sync := clock
        //u_slcg_op(i).io.somnia_clock.tmc2slcg_disable_clock_gating :=io.somnia_clock.tmc2slcg_disable_clock_gating

        u_slcg_op(i).io.slcg_en(0) := io.slcg_op_en(i)
        somnia_op_gated_clk(i) := u_slcg_op(i).io.somnia_core_gated_clk
    }

}

object SOMNIA_CMAC_coreDriver extends App {
  implicit val conf: somniaConfig = new somniaConfig
  chisel3.Driver.execute(args, () => new SOMNIA_CMAC_core)
}

