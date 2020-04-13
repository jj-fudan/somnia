package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CSB_Tests(c:NV_NVDLA_CSB_LOGIC) extends PeekPokeTester(c){
  val req2 = "b000000001000000000000000000000000000010000000000000000000001011".U
  poke(c.io.csb2dp.req.valid,true)
  poke(c.io.csb2dp.req.bits,req2)
  step(1)
  expect(c.io.reg.offset,"h2c".U)
  expect(c.io.reg.wr_en,true)
  expect(c.io.reg.wr_data,8)
  
}


class SOMNIA_CSB_Tester extends ChiselFlatSpec{
  behavior of "NV_NVDLA_CSB_LOGIC"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new NV_NVDLA_CSB_LOGIC())(c => new SOMNIA_CSB_Tests(c)) should be (true)
    }
  }
}
