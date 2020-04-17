package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._
//read data 2 cycles
class SOMNIA_CACC_assembly_bufferTests (c:SOMNIA_CACC_assembly_buffer) extends PeekPokeTester(c){
      implicit val conf: somniaConfig = new somniaConfig
   for(i<- 0 until 100){
      val rd_addr = i%15+1
      val wr_addr = rd_addr
      val di = i*10
     //w1
      poke(c.io.abuf_wr.addr.valid,true)
      poke(c.io.abuf_wr.addr.bits,wr_addr-1)
      poke(c.io.abuf_wr.data,di)
 step(1)
    //w2
      poke(c.io.abuf_wr.addr.valid,false)
     poke(c.io.abuf_wr.addr.bits,wr_addr)
      poke(c.io.abuf_wr.data,1)
    //r1
      poke(c.io.abuf_rd.addr.valid,true)
      poke(c.io.abuf_rd.addr.bits,rd_addr-1)
      step(1)
      poke(c.io.abuf_rd.addr.valid,false)
      step(1)
      expect(c.io.abuf_rd.data,di)
      
   }
}
class SOMNIA_CACC_assembly_buffer_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_assembly_buffer"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_assembly_buffer())(c => new SOMNIA_CACC_assembly_bufferTests(c)) should be (true)
    }
  }
}
