package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CACC_regfile_Tests (c:SOMNIA_CACC_regfile) extends PeekPokeTester(c){
  poke(c.io.csb2cacc.req.valid,true)
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000010000000000000000000001011".U)
  step(1)
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000011011".U)
  step(1)
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000000010".U)
  expect(c.io.reg2dp_field.clip_truncate,8)
  step(1)
  expect(c.io.reg2dp_field.bn_relu_bypass,1)
  step(4)
  expect(c.io.slcg_op_en,"b1111111".U)
  expect(c.io.reg2dp_op_en,true)
}

class SOMNIA_CACC_regfile_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_regfile"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_regfile())(c => new SOMNIA_CACC_regfile_Tests(c)) should be (true)
    }
  }
}
