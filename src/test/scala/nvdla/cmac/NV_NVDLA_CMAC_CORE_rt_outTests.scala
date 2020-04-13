package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util.ValidIO


class SOMNIA_CMAC_CORE_rt_outTests(c: SOMNIA_CMAC_CORE_rt_out) extends PeekPokeTester(c) {
 
  implicit val conf: somniaConfig = new somniaConfig
  poke(c.io.out.valid,true)
  poke(c.io.out.bits.pd,"b111000000".U)
  step(1)
  poke(c.io.out.valid,false)
  step(1)
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b111000000".U)
  step(1)
  expect(c.io.mac2accu.valid,false)
  expect(c.io.mac2accu.bits.pd,"b111000000".U)


  
}

class SOMNIA_CMAC_CORE_rt_outTester extends ChiselFlatSpec {

  behavior of "SOMNIA_CMAC_CORE_rt_out"
  backends foreach {backend =>
    it should s"correctly retiming wt and dat $backend" in {
      implicit val conf: somniaConfig = new somniaConfig
      Driver(() => new SOMNIA_CMAC_CORE_rt_out())(c => new SOMNIA_CMAC_CORE_rt_outTests(c)) should be (true)
    }
  }
}
