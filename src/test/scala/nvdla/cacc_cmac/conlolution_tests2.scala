package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_convolution_Tests2 (c:SOMNIA_convolution) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  import scala.util.Random
  poke(c.io.somnia_core_rstn,true)
  poke(c.io.csb2cacc.req.valid,true)
  poke(c.io.cacc2ppu_pd_ready,true)
  poke(c.io.somnia_core_rstn,true)//no reset
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000000000000000000000001011".U)//truncate=0
  step(1)
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000011011".U)//bypass =1
  step(1)
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000000010".U)
  step(1)
  ///////////////////
  var psum = Array.ofDim[BigInt](8,8)//to store partial sum
  for(i<-0 to 7)
    for(j<-0 to 7)
    psum(i)(j) = 0
  var wt_actv_data = Array.ofDim[Int](8,8)//to store actv wt
  var wt_actv_mask = Array.ofDim[Boolean](8,8)
  for (i <- 0 to 7){
     for (j <- 0 to 7){
       wt_actv_data(i)(j)=0
       wt_actv_mask(i)(j)=false
     }
  }
  val spc = Random.nextInt(9)+1//stripe per channel
  val cpl = Random.nextInt(9)+1//channel per layer
for(layer <-0 to cpl-1){
  for(i <- 0 to spc-1){
  val wt =Array.ofDim[Int](8,8)//input wt
  val dat=Array.ofDim[Int](8,8)//input data
  val dat_mask=Array.ofDim[Boolean](8,8)//input data_mask
  val wt_mask=Array.ofDim[Boolean](8,8)//input wt_mask
  var channel_end = 0
  var channel_st  = 0
  var layer_end = 0
  if(i == spc-1){  //channel end sign
  channel_end = 1}
  else{
  channel_end = 0}
  if(i == 0){
  channel_st = 1}
  else{
  channel_st = 0}
  if(layer == cpl-1 & i==spc-1){//layer_end sign
  layer_end=1}
  else{
  layer_end=0}
  for(ii <- 0 to 7){//initialization
    for(jj <-0 to 7){
      wt(ii)(jj)= Random.nextInt(2*(1<<(conf.CMAC_BPE-1)-1)) - (1 << (conf.CMAC_BPE-1) - 1)
      dat(ii)(jj)=Random.nextInt(2*(1<<(conf.CMAC_BPE-1)-1)) - (1 << (conf.CMAC_BPE-1) - 1)
      dat_mask(ii)(jj) =Random.nextBoolean()
      wt_mask(ii)(jj) =Random.nextBoolean() 
    }
  }
  poke(c.io.sc2mac_wt.valid,true)//Input data of one strip in eight cycles
  for( n <- 0 to conf.CMAC_ATOMK-1){//wt1
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(0)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(0)(n))
  if(n == 0)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat1
  poke(c.io.sc2mac_dat.bits.pd,(1<<5)+(channel_end<<7)+(layer_end<<8))//stripe start
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(0)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(0)(n))
  }
  step(1)
 
  poke(c.io.sc2mac_wt.valid,true)//wt2
  for( n <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(1)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(1)(n))
  if(n == 1)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat2
  poke(c.io.sc2mac_dat.bits.pd,(channel_end<<7)+(layer_end<<8))
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(1)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(1)(n))
  }
  step(1)

  poke(c.io.sc2mac_wt.valid,true)
  for( n <- 0 to conf.CMAC_ATOMK-1){//wt3
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(2)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(2)(n))
  if(n == 2)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat3
  poke(c.io.sc2mac_dat.bits.pd,(channel_end<<7)+(layer_end<<8))
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(2)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(2)(n))
  }
  step(1)
  
  poke(c.io.sc2mac_wt.valid,true)
  for( n <- 0 to conf.CMAC_ATOMK-1){//wt4
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(3)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(3)(n))
  if(n == 3)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat4
  poke(c.io.sc2mac_dat.bits.pd,(channel_end<<7)+(layer_end<<8))
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(3)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(3)(n))
  }
  step(1)
 
  poke(c.io.sc2mac_wt.valid,true)//wt5
  for( n <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(4)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(4)(n))
  if(n == 4)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat5
  poke(c.io.sc2mac_dat.bits.pd,(channel_end<<7)+(layer_end<<8))
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(4)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(4)(n))
  }
  step(1)
 
  poke(c.io.sc2mac_wt.valid,true)//wt6
  for( n <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(5)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(5)(n))
  if(n == 5)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat6
  poke(c.io.sc2mac_dat.bits.pd,(channel_end<<7)+(layer_end<<8))
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(5)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(5)(n))
  }
  step(1)

  poke(c.io.sc2mac_wt.valid,true)
  for( n <- 0 to conf.CMAC_ATOMK-1){//wt7
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(6)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(6)(n))
  if(n == 6)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat7
  poke(c.io.sc2mac_dat.bits.pd,(channel_end<<7)+(layer_end<<8))
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(6)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(6)(n))
  }
  step(1)

  poke(c.io.sc2mac_wt.valid,true)
  for( n <- 0 to conf.CMAC_ATOMK-1){//wt8
  poke(c.io.sc2mac_wt.bits.mask(n),wt_mask(7)(n))
  poke(c.io.sc2mac_wt.bits.data(n),wt(7)(n))
  if(n == 7)
  poke(c.io.sc2mac_wt.bits.sel(n),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(n),0)
  }
  poke(c.io.sc2mac_dat.valid,true)//dat8
  poke(c.io.sc2mac_dat.bits.pd,(1<<6)+(channel_end<<7)+(layer_end<<8))//stripe end
  for(n<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(n),dat_mask(7)(n))
  poke(c.io.sc2mac_dat.bits.data(n),dat(7)(n))
  }
  step(1)
  //cmac
  var sum_out=Array.ofDim[BigInt](8,8)
  var mout=Array.ofDim[BigInt](8)
  for(ii <-0 to 7){
    for(jj <-0 to 7){
       for(kk<-0 to 7){
        if(dat_mask(ii)(kk) & wt_actv_mask(jj)(kk)){
        mout(kk) =dat(ii)(kk)*wt_actv_data(jj)(kk) 
        }
        else{
        mout(kk)=0
        }
       }
       sum_out(ii)(jj)=mout.reduce(_+_)
    }      
  }
  for(ii<-0 to 7)
    for(jj<-0 to 7){
    wt_actv_data(ii)(jj) = wt(ii)(jj)
    wt_actv_mask(ii)(jj) = wt_mask(ii)(jj)
    }
  //cacc
  val bias = BigInt("4294967296")
  var fsum = Array.ofDim[BigInt](8,8)
  var calout = Array.ofDim[BigInt](8,8)
  if(channel_st == 1){//calculator
  for(ii <-0 to 7)
    for(jj <-0 to 7){
    calout(ii)(jj) = sum_out(ii)(jj)}
  }
  else{
  for(ii<-0 to 7)
    for(jj<-0 to 7)
    calout(ii)(jj) = psum(ii)(jj) + sum_out(ii)(jj)
  }     
  if(channel_end == 0){//not channel end accumulate psum
  for(ii<-0 to 7)
     for(jj<-0 to 7)
     psum(ii)(jj) = calout(ii)(jj)
  }
  else{//channel end deliver final sum to ppu
  for(ii<-0 to 7)
     for(jj<-0 to 7){
     if(calout(ii)(jj) <0)
     fsum(ii)(jj) = calout(ii)(jj)+bias
     else
     fsum(ii)(jj) = calout(ii)(jj)}
  }
  if(channel_end == 1){//wait for read final data
  poke(c.io.sc2mac_dat.valid,false)
  poke(c.io.sc2mac_wt.valid,false)
  step(6)
  for(ii <-0 to 7){
  step(1)
  //poke(c.io.cacc2ppu_pd_ready,true)
  expect(c.io.cacc2ppu_pd_valid,true)
  expect(c.io.out1,fsum(ii)(0))
  expect(c.io.out2,fsum(ii)(1))
  expect(c.io.out3,fsum(ii)(2))
  expect(c.io.out4,fsum(ii)(3))
  step(1)
  expect(c.io.cacc2ppu_pd_valid,true)
  expect(c.io.out1,fsum(ii)(4))
  expect(c.io.out2,fsum(ii)(5))
  expect(c.io.out3,fsum(ii)(6))
  expect(c.io.out4,fsum(ii)(7))
  step(1)
  expect(c.io.cacc2ppu_pd_valid,false)}
  }

  }}
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
