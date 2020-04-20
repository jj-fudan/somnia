package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_convolution_Tests (c:SOMNIA_convolution) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  import scala.util.Random
//for(i <-0 to 10){
  val wt1 =Array.ofDim[Int](8,8)
  val wt2 =Array.ofDim[Int](8,8)
  val dat1 = Array.ofDim[Int](8,8)
  val dat2 = Array.ofDim[Int](8,8)
  val dat1_mask = Array.ofDim[Boolean](8,8) 
  val wt1_mask  = Array.ofDim[Boolean](8,8)
  val dat2_mask = Array.ofDim[Boolean](8,8) 
  val wt2_mask  = Array.ofDim[Boolean](8,8)
  for (i <-0 to 7)
    for (j <-0 to 7){
    wt1(i)(j)= Random.nextInt(2*(1<<(conf.CMAC_BPE-1)-1)) - (1 << (conf.CMAC_BPE-1) - 1)
    wt2(i)(j)=Random.nextInt(2*(1<<(conf.CMAC_BPE-1)-1)) - (1 << (conf.CMAC_BPE-1) - 1)
    dat1(i)(j)=Random.nextInt(2*(1<<(conf.CMAC_BPE-1)-1)) - (1 << (conf.CMAC_BPE-1) - 1)
    dat2(i)(j)=Random.nextInt(2*(1<<(conf.CMAC_BPE-1)-1)) - (1 << (conf.CMAC_BPE-1) - 1)
    dat1_mask(i)(j) =Random.nextBoolean()
    wt1_mask(i)(j) =Random.nextBoolean() 
    dat2_mask(i)(j) =Random.nextBoolean()
    wt2_mask(i)(j) =Random.nextBoolean() 
    }
  poke(c.io.somnia_core_rstn,true)
  poke(c.io.csb2cacc.req.valid,true)
  poke(c.io.cacc2ppu_pd_ready,false)
  poke(c.io.somnia_core_rstn,true)//no reset
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000000000000000000000001011".U)
  step(1)//truncate=0
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000011011".U)//bypass =1
  step(1)
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000000010".U)
  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(0)(i))
 // poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(0)(i))
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//1

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(1)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(1)(i)) 
  if(i == 1)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//2

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(2)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(2)(i))
  if(i == 2)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//3

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(3)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(3)(i))
  if(i == 3)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//4

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(4)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(4)(i))
  if(i == 4)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//5

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(5)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(5)(i))
  if(i == 5)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//6

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(6)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(6)(i))
  if(i == 6)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//7

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt1_mask(7)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(7)(i))
  if(i == 7)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }//wt1 ok
  step(1)//8
  //wt2st
  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(0)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(0)(i))
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  //dat1 st
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000100000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(0)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(0)(i))
  }

  step(1)//9

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(1)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(1)(i))
  if(i == 1)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(1)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(1)(i))

  }
  step(1)//10

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(2)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(2)(i))
  if(i == 2)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(2)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(2)(i))
  }
  
  step(1)//11

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(3)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(3)(i))
  if(i == 3)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(3)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(3)(i))
  }
  step(1)//12

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(4)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(4)(i))
  if(i == 4)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(4)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(4)(i))
  }
  step(1)//13

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(5)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(5)(i))
  if(i == 5)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(5)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(5)(i))
  }
  step(1)//14
                      //cacc st
  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(6)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(6)(i))
  if(i == 6)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(6)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(6)(i))
  }
  
  step(1)//15

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),wt2_mask(7)(i))
//poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(7)(i))
  if(i == 7)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b001000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat1_mask(7)(i))
//poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(7)(i))
  }
  
  step(1)
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b010100000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(0)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(0)(i))
  }
  
  step(1)//17
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b010000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(1)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(1)(i))
  }
  
  step(1)//18
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b010000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(2)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(2)(i))
  }
  
  step(1)//19
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b010000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(3)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(3)(i))
  }
  
  step(1)//20
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b010000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(4)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(4)(i))
  }
  
  step(1)//21
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b010000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(5)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(5)(i))
  }
  
  step(1)//22
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b010000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(6)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(6)(i))
  }
  
  step(1)//23
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b011000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),dat2_mask(7)(i))
  poke(c.io.sc2mac_dat.bits.data(i),dat2(7)(i))
  }
  
  step(1)//24
  
  poke(c.io.sc2mac_dat.valid,false)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  
  step(1)//25
  
  step(1)//26
  
  step(1)//27
 
  step(1)//28
  
  step(1)//29
  var mout1 =  Array.ofDim[BigInt](8,8)
  var mout2 =  Array.ofDim[BigInt](8,8)
  var pout1  = Array.fill(conf.CMAC_ATOMC){0}
  var pout2  = Array.fill(conf.CMAC_ATOMC){0}
  for(i <-0 to 7)
    for(j <-0 to 7){
       for(k<-0 to 7){
        if(dat1_mask(i)(k) & wt1_mask(j)(k)){
        pout1(k) =dat1(i)(k)*wt1(j)(k) 
        }
        else{
        pout1(k)=0
        }
        if(dat2_mask(i)(k) & wt2_mask(j)(k)){
        pout2(k) =dat2(i)(k)*wt2(j)(k)
        }
        else{
        pout2(k)=0
        }
       }
       mout1(i)(j)=pout1.reduce(_+_)
       mout2(i)(j)=pout2.reduce(_+_)
    //mout1(i)(j) = dat1(i)(0)*wt1(j)(0) +dat1(i)(1)*wt1(j)(1) +dat1(i)(2)*wt1(j)(2) +dat1(i)(3)*wt1(j)(3) +dat1(i)(4)*wt1(j)(4) +dat1(i)(5)*wt1(j)(5) +dat1(i)(6)*wt1(j)(6) +dat1(i)(7)*wt1(j)(7)
    //mout2(i)(j) = dat2(i)(0)*wt2(j)(0) +dat2(i)(1)*wt2(j)(1) +dat2(i)(2)*wt2(j)(2) +dat2(i)(3)*wt2(j)(3) +dat2(i)(4)*wt2(j)(4) +dat2(i)(5)*wt2(j)(5) +dat2(i)(6)*wt2(j)(6) +dat2(i)(7)*wt2(j)(7)
  }
  
  val out = Array.ofDim[BigInt](8,8)
  for(i<-0 to 7)
    for(j<-0 to 7){
    out(i)(j) = mout1(i)(j)+mout2(i)(j)
  }
  val bias = BigInt("4294967296") 
  val fout = Array.ofDim[BigInt](8,8)
  for(i<-0 to 7)
    for(j<-0 to 7){
    if(out(i)(j)<0)
    fout(i)(j) = out(i)(j) +bias
    else
    fout(i)(j) = out(i)(j)
  }
  step(10)//30
  for(i <-0 to 7){
  step(1)//31
  poke(c.io.cacc2ppu_pd_ready,true)
  expect(c.io.cacc2ppu_pd_valid,true)//out1
  expect(c.io.out1,fout(i)(0))
  expect(c.io.out2,fout(i)(1))
  expect(c.io.out3,fout(i)(2))
  expect(c.io.out4,fout(i)(3))
  step(1)//32
  expect(c.io.cacc2ppu_pd_valid,true)//out1
  expect(c.io.out1,fout(i)(4))
  expect(c.io.out2,fout(i)(5))
  expect(c.io.out3,fout(i)(6))
  expect(c.io.out4,fout(i)(7))
  step(1)
  expect(c.io.cacc2ppu_pd_valid,false)}
  step(1)
  //poke(c.io.somnia_core_rstn,false)
  poke(c.io.cacc2ppu_pd_ready,false)
  step(1)
//}
}
class SOMNIA_convolution_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_convolution"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_convolution())(c => new SOMNIA_convolution_Tests(c)) should be (true)
    }
  }
}

