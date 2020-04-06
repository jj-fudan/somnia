package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

import scala.util.Random

class SOMNIA_CMAC_Tests(c:SOMNIA_cmac) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  poke(c.io.somnia_core_rstn,true)
  val req ="b000000001000000000000000000000000000000010000000000000000000010".U
  poke(c.io.csb2cmac_a.req.valid,true)
  poke(c.io.csb2cmac_a.req.bits,req)
  step(10)
  //data
  def sc2mac_dat(size:Int = 0): Map[String, List[Int]]={
	val mask = List.fill[Int](conf.CMAC_ATOMC)(1)
	val data = List.fill[Int](conf.CMAC_ATOMC)(Random.nextInt(256))
	val valid = List.fill[Int](1)(1)
        (c.io.sc2mac_dat.bits.mask, mask).zipped.foreach(poke(_, _))
        (c.io.sc2mac_dat.bits.data, data).zipped.foreach(poke(_, _))
        poke(c.io.sc2mac_dat.valid, valid(0))
        Map( "mask"->mask, "data"->data, "valid"->valid)
        }
	//wt
  def sc2mac_wt(size:Int = 0): Map[String, List[Int]] ={
        val sel = List.fill[Int](conf.CMAC_ATOMK)(1)
        val mask = List.fill[Int](conf.CMAC_ATOMC)(1)
        val wt = List.fill[Int](conf.CMAC_ATOMC)(Random.nextInt(256))
        val valid = List.fill[Int](1)(1)
        (c.io.sc2mac_wt.bits.sel, sel).zipped.foreach(poke(_, _))
        (c.io.sc2mac_wt.bits.mask, mask).zipped.foreach(poke(_, _))
        (c.io.sc2mac_wt.bits.data, wt).zipped.foreach(poke(_, _))
        poke(c.io.sc2mac_wt.valid, valid(0))
        Map("sel"->sel, "mask"->mask, "wt"->wt, "valid"->valid)
        }
  val data=sc2mac_dat()
  val wt = sc2mac_wt()
  val pd1="b000100000".U
  val pd2="b001000000".U
  poke(c.io.sc2mac_dat.bits.pd,pd1)
  step(3)
  poke(c.io.sc2mac_dat.bits.pd,pd2)
  step(15)
  expect(c.io.mac2accu.valid,true.B)
  val mout = Array.fill(conf.CMAC_ATOMC){0}
  for(i <- 0 to conf.CMAC_ATOMC-1){
  mout(i) = data("data")(i)*wt("wt")(i)}
  val sum_out = mout.reduce(_+_)
  for(i <- 0 to conf.CMAC_ATOMK-1){
	   expect(c.io.mac2accu.bits.mask(i),true)
	     if(c.io.mac2accu.bits.mask(i) == 1.U){
	        expect(c.io.mac2accu.bits.data(i),sum_out)
	}}
	expect(c.io.mac2accu.bits.pd,pd2)	        
}

class SOMNIA_CMAC_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CAMC"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_cmac())(c => new SOMNIA_CMAC_Tests(c)) should be (true)
    }
  }
}
