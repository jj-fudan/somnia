package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util.ValidIO


class SOMNIA_CMAC_CORE_rt_inTests(c: SOMNIA_CMAC_CORE_rt_in) extends PeekPokeTester(c) {
 
  implicit val conf: somniaConfig = new somniaConfig
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b111000000".U)
  step(1)
  poke(c.io.sc2mac_dat.valid,false)
  step(1)
  expect(c.io.in_dat.valid,true)
  expect(c.io.in_dat.bits.pd,"b111000000".U)
  step(1)
  expect(c.io.in_dat.valid,false)
  expect(c.io.in_dat.bits.pd,"b111000000".U)
 


}

class SOMNIA_CMAC_CORE_rt_inTester extends ChiselFlatSpec {

  behavior of "SOMNIA_CMAC_CORE_rt_in"
  backends foreach {backend =>
    it should s"correctly retiming wt and dat $backend" in {
      implicit val conf: somniaConfig = new somniaConfig
      Driver(() => new SOMNIA_CMAC_CORE_rt_in())(c => new SOMNIA_CMAC_CORE_rt_inTests(c)) should be (true)
    }
  }
}
