package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util.ValidIO

import scala.util.Random

class SOMNIA_CMAC_CORE_Tests (c: SOMNIA_CMAC_core) extends PeekPokeTester(c){
   implicit val conf: somniaConfig = new somniaConfig	
  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),1)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//1

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),2)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 1)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//2

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),3)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 2)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//3

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),4)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 3)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//4

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),5)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 4)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//5

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),6)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 5)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//6

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),7)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 6)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//7

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),8)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 7)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }//wt1 ok
  step(1)//8
  //wt2st
  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),9)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  //dat1 st
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000100000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  step(1)//9

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),10)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 1)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  step(1)//10

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),11)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 2)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  step(1)//11

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),12)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 3)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  step(1)//12

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),13)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 4)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  step(1)//13

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),14)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 5)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  step(1)//14

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),15)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 6)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000100000".U)
  step(1)//15

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.data(i),16)
  else
  poke(c.io.sc2mac_wt.bits.data(i),0) 
  if(i == 7)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b001000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//16
  
  poke(c.io.sc2mac_wt.valid,false)
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110100000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//17
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//18
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//19
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//20
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//21
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b001000000".U)
  step(1)//22
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
    expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b110100000".U)
  step(1)//23
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b111000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.sc2mac_dat.bits.data(i),1)
  else
  poke(c.io.sc2mac_dat.bits.data(i),0)
  }
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b110000000".U)
  step(1)//24
  
  poke(c.io.sc2mac_dat.valid,false)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+1)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b110000000".U)
  step(1)//25
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b110000000".U)
  step(1)//26
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b110000000".U)
  step(1)//27
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b110000000".U)
  step(1)//28
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b110000000".U)
  step(1)//29
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b111000000".U)
  step(1)//30
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  expect(c.io.mac2accu.valid,false)
  expect(c.io.mac2accu.bits.pd,"b111000000".U)
  step(1)//31
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  step(1)//32
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
  step(1)
  for(i<-0 to conf.CMAC_ATOMK-1){
  expect(c.io.mac2accu.bits.mask(i),false)
  expect(c.io.mac2accu.bits.data(i),i+9)
  }
}
class SOMNIA_CMAC_CORE_Tester extends ChiselFlatSpec{
	behavior of "SOMNIA_CMAC_core"
	backends foreach {backend =>
	 it should s"correctly perform core logic $backend" in {
	     implicit val nvconf: somniaConfig = new somniaConfig
	     Driver(()=>new SOMNIA_CMAC_core())(c=>new SOMNIA_CMAC_CORE_Tests(c)) should be (true)
   }
  }
}
