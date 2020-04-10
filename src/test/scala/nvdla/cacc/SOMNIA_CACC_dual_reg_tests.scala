package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CACC_dual_reg_Tests(c:SOMNIA_CACC_dual_reg) extends PeekPokeTester(c){ 
  poke(c.io.reg.wr_en,true)
  poke(c.io.reg.offset,"h2c".U)
  poke(c.io.reg.wr_data,8)
  step(1)
  expect(c.io.field.clip_truncate,8)
  poke(c.io.reg.offset,"h6c".U)
  poke(c.io.reg.wr_data,1)
  step(1)
  expect(c.io.field.bn_relu_bypass,1)

}

class SOMNIA_CACC_dual_reg_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_dual_reg"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_dual_reg())(c => new SOMNIA_CACC_dual_reg_Tests(c)) should be (true)
    }
  }
}
