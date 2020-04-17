package nvdla

import chisel3._
import chisel3.experimental._
import chisel3.util._

class SOMNIA_result (implicit val conf: somniaConfig) extends Module{
   val io = IO(new Bundle {
       val cacc2ppu_pd = Input(UInt(conf.CACC_PPU_WIDTH.W))
       val out1 = Output(UInt(conf.CACC_FINAL_WIDTH.W))
       val out2 = Output(UInt(conf.CACC_FINAL_WIDTH.W))
       val out3 = Output(UInt(conf.CACC_FINAL_WIDTH.W))
       val out4 = Output(UInt(conf.CACC_FINAL_WIDTH.W))
       val pd   = Output(UInt(2.W))
       })
   io.out1 := io.cacc2ppu_pd(31,0)
   io.out2 := io.cacc2ppu_pd(63,32)
   io.out3 := io.cacc2ppu_pd(95,64)
   io.out4 := io.cacc2ppu_pd(127,96)
   io.pd   := io.cacc2ppu_pd(129,128)
}

