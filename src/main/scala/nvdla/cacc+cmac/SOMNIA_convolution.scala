package nvdla

import chisel3._
import chisel3.experimental._
import chisel3.util._

class SOMNIA_convolution(implicit val conf: somniaConfig) extends Module{
    val io = IO(new Bundle {
    //general clock
    val somnia_clock = Flipped(new somnia_clock_if)
    val somnia_core_rstn = Input(Bool())
    //csb
    val csb2cmac_a = new csb2dp_if
    val csb2cacc = new csb2dp_if 
    //odif
    val sc2mac_dat = Flipped(ValidIO(new csc2cmac_data_if))
    val sc2mac_wt = Flipped(ValidIO(new csc2cmac_wt_if))
    //sdp
    val cacc2ppu_pd = DecoupledIO(UInt((conf.CACC_PPU_WIDTH).W))
    //csc
    val accu2sc_credit_size = ValidIO((UInt(3.W)))

    //glb
    val cacc2glb_done_intr_pd = Output(UInt(2.W)) 
    val pwrbus_ram_pd = Input(UInt(32.W))     
    })
withReset(!io.somnia_core_rstn){
    val u_cmac = Module(new SOMNIA_cmac)
    val u_cacc = Module(new SOMNIA_cacc)
    //cmac
    u_cmac.io.somnia_clock <> io.somnia_clock
    u_cmac.io.somnia_core_rstn <> io.somnia_core_rstn
    u_cmac.io.csb2cmac_a <> io.csb2cmac_a
    u_cacc.io.mac2accu :=  u_cmac.io.mac2accu 
    u_cmac.io.sc2mac_dat <> io.sc2mac_dat
    u_cmac.io.sc2mac_wt <> io.sc2mac_wt
    //cacc
    u_cacc.io.somnia_clock <> io.somnia_clock
    u_cacc.io.somnia_core_rstn <> io.somnia_core_rstn
    u_cacc.io.csb2cacc <> io.csb2cacc
    u_cacc.io.cacc2ppu_pd <> io.cacc2ppu_pd
    u_cacc.io.accu2sc_credit_size <> io.accu2sc_credit_size
    u_cacc.io.cacc2glb_done_intr_pd <> io.cacc2glb_done_intr_pd
    u_cacc.io.pwrbus_ram_pd <> io.pwrbus_ram_pd
    
}}

object SOMNIA_convolutionDriver extends App {
  implicit val conf: somniaConfig = new somniaConfig
  chisel3.Driver.execute(args, () => new SOMNIA_convolution())
}
