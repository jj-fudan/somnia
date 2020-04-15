package nvdla

import chisel3._
import chisel3.experimental._

class SOMNIA_slcg(input_num: Int = 1, io_slcg_disable: Boolean = false) extends Module {
   val io = IO(new Bundle {
        //in clock
        val somnia_clock = Flipped(new somnia_clock_if)

        //enable
        val slcg_disable = if(io_slcg_disable) Some(Input(Bool())) else None  
        val slcg_en = Input(Vec(input_num, Bool()))
        
        //out clock
        val somnia_core_gated_clk = Output(Clock())

    })

withClock(io.somnia_clock.somnia_core_clk){  

    val slcg_enable = if(io_slcg_disable) Some(RegInit(false.B)) else None
    val cfg_clk_en = Wire(Bool())
    if(io_slcg_disable){
        slcg_enable.get := !io.slcg_disable.get
        cfg_clk_en := io.slcg_en.asUInt.andR & slcg_enable.get
    }
    else{
        cfg_clk_en := io.slcg_en.asUInt.andR
    }
    
    val somnia_core_clk_slcg_en = cfg_clk_en | io.somnia_clock.dla_clk_ovr_on_sync.asUInt.toBool |
                                   (io.somnia_clock.tmc2slcg_disable_clock_gating|io.somnia_clock.global_clk_ovr_on_sync.asUInt.toBool)

    val nvdla_core_clk_slcg = Module(new NV_CLK_gate_power)
    nvdla_core_clk_slcg.io.clk := io.somnia_clock.somnia_core_clk
    nvdla_core_clk_slcg.io.clk_en := somnia_core_clk_slcg_en
    io.somnia_core_gated_clk := nvdla_core_clk_slcg.io.clk_gated
}}


