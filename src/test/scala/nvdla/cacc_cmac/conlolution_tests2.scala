package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_convolution_Tests2 (c:SOMNIA_convolution) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  import scala.util.Random
  var wt1 =Array.ofDim[Int](8,8)
  poke(c.io.somnia_core_rstn,true)
  poke(c.io.csb2cacc.req.valid,true)
  poke(c.io.cacc2ppu_pd_ready,false)
  poke(c.io.somnia_core_rstn,true)//no reset
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000000000000000000000001011".U)
  step(1)//truncate=0
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000011011".U)//bypass =1
  step(1)
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000000010".U)
  step(1)
  ///////////////////
  val dat1_mask = Array.ofDim[Boolean](8,8) 
  val wt1_mask  = Array.ofDim[Boolean](8,8)
  val dat2_mask = Array.ofDim[Boolean](8,8) 
  val wt2_mask  = Array.ofDim[Boolean](8,8)
  for (i <-0 to 7)
    for (j <-0 to 7){
    dat1_mask(i)(j) = Random.nextBoolean()
    wt1_mask(i)(j) = Random.nextBoolean() 
    dat2_mask(i)(j) = Random.nextBoolean()
    wt2_mask(i)(j) = Random.nextBoolean() 
    }
for(i <- 0 to 10){
  val spc = Random.nextInt(9)+1
  for(j<- 0 to spc){
  
   } 
  }
}

class SOMNIA_convolution_Tester2 extends ChiselFlatSpec{
  behavior of "SOMNIA_convolution"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_convolution())(c => new SOMNIA_convolution_Tests2(c)) should be (true)
    }
  }
}
