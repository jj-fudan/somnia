package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util.ValidIO

import scala.util.Random

class SOMNIA_CMAC_CORE_Testss (c: SOMNIA_CMAC_core) extends PeekPokeTester(c){
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b111000000".U)
  step(1)
  poke(c.io.sc2mac_dat.valid,false)
  step(5)
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b111000000".U)



}
class SOMNIA_CMAC_CORE_Tester2 extends ChiselFlatSpec{
	behavior of "SOMNIA_CMAC_core"
	backends foreach {backend =>
	 it should s"correctly perform core logic $backend" in {
	     implicit val nvconf: somniaConfig = new somniaConfig
	     Driver(()=>new SOMNIA_CMAC_core())(c=>new SOMNIA_CMAC_CORE_Testss(c)) should be (true)
   }
  }
}
