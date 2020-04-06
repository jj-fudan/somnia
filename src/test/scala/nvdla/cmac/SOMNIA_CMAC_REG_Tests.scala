package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CMAC_REG_Tests(c:SOMNIA_CMAC_reg) extends PeekPokeTester(c){
  val addr1 = "h1".asUInt(22.W)    //offest=4
  val addr2 = "h2".asUInt(22.W)    //offest=8
  val wadt1 = "h0".asUInt(32.W)  
  val wadt2 = "h1".asUInt(32.W)  
  val write = "b1".asUInt(1.W)
  val nposted = "b1".asUInt(1.W)
  val srcpriv = "b0".asUInt(1.W)
  val wrbe = "h0".asUInt(4.W)
  val level = "h0".asUInt(2.W)
 // val req1 = Cat(addr1,wadt1,write,nposted,srcpriv,wrbe,level)
 // val req1 = (1 + (1 << 54) + (1 << 55) )
  //poke(c.io.dp2reg_done,false)
  poke(c.io.csb2cmac_a.req.valid,true)
 // poke(c.io.csb2cmac_a.req.bits,req1)
 // step(10)
 // val req2 = Cat(addr2,wadt2,write,nposted,srcpriv,wrbe,level)
  val req2 ="b000000001000000000000000000000000000000010000000000000000000010".U
  poke(c.io.csb2cmac_a.req.valid,true)
  poke(c.io.csb2cmac_a.req.bits,req2)
  step(10)
  val op_en = "b11111111111".asUInt(11.W)
  expect(c.io.slcg_op_en,op_en)
  
}


class SOMNIA_CMAC_REG_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CAMC_reg"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CMAC_reg())(c => new SOMNIA_CMAC_REG_Tests(c)) should be (true)
    }
  }
}
