package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CACC_INT8_Tests (c:SOMNIA_CACC_CALC_int8) extends PeekPokeTester(c){
  val in_data = "b0111010001011100011000".U
  val in_op   = "b0111111111111111101111011110000000".U
  poke(c.io.in_data,in_data)
  poke(c.io.in_op,in_op)
  poke(c.io.in_op_valid,true)
  poke(c.io.in_sel,false)
  poke(c.io.in_valid,true)
  poke(c.io.cfg_truncate,8)
  poke(c.io.cfg_relu_bypass,false)
  step(2)
  expect(c.io.out_partial_valid,true)
  expect(c. io.out_partial_data,"b0111111111111111111111111111111111".U)
  expect(c.io.out_final_valid,false)
  expect(c.io.out_final_sat,false)
  expect(c.io.out_final_data,0)
  poke(c.io.in_sel,true)
  step(2)
  expect(c.io.out_partial_valid,false)
  expect(c.io.out_final_valid,true)
  expect(c.io.out_final_sat,false)
  expect(c.io.out_final_data,"b00000010000000000000000000000000".U)
  poke(c.io.cfg_truncate,0)
  step(2)
  expect(c.io.out_partial_valid,false)
  expect(c.io.out_final_valid,true)
  expect(c.io.out_final_sat,true)
  expect(c.io.out_final_data,"b01111111111111111111111111111111".U)

}

class SOMNIA_CACC_int8_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_int8"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_CALC_int8())(c => new SOMNIA_CACC_INT8_Tests(c)) should be (true)
    }
  }
}
