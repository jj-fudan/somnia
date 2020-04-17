package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

import scala.util.Random

class SOMNIA_CMAC_Tests(c:SOMNIA_cmac) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  poke(c.io.somnia_core_rstn,true)
    import scala.util.Random
for(i <-0 to 4){
  val wt1 =Array.ofDim[Int](8,8)
  val wt2 =Array.ofDim[Int](8,8)
  val dat1 = Array.ofDim[Int](8,8)
  val dat2 = Array.ofDim[Int](8,8)
  for (i <-0 to 7)
    for (j <-0 to 7){
    wt1(i)(j)=Random.nextInt(128)
    wt2(i)(j)=Random.nextInt(128)
    dat1(i)(j)=Random.nextInt(128)
    dat2(i)(j)=Random.nextInt(128) 
    //dat1(i)(j)=1
    //dat2(i)(j)=1
    }
  val mout1 =  Array.ofDim[Int](8,8)
  val mout2 =  Array.ofDim[Int](8,8)
  for(i <-0 to 7)
    for(j <-0 to 7){
    mout1(i)(j) = dat1(i)(0)*wt1(j)(0) +dat1(i)(1)*wt1(j)(1) +dat1(i)(2)*wt1(j)(2) +dat1(i)(3)*wt1(j)(3) +dat1(i)(4)*wt1(j)(4) +dat1(i)(5)*wt1(j)(5) +dat1(i)(6)*wt1(j)(6) +dat1(i)(7)*wt1(j)(7)
    mout2(i)(j) = dat2(i)(0)*wt2(j)(0) +dat2(i)(1)*wt2(j)(1) +dat2(i)(2)*wt2(j)(2) +dat2(i)(3)*wt2(j)(3) +dat2(i)(4)*wt2(j)(4) +dat2(i)(5)*wt2(j)(5) +dat2(i)(6)*wt2(j)(6) +dat2(i)(7)*wt2(j)(7) 
  }
  
  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(0)(i))
  if(i == 0)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//1

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(1)(i)) 
  if(i == 1)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//2

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(2)(i))
  if(i == 2)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//3

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(3)(i))
  if(i == 3)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//4

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(4)(i))
  if(i == 4)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//5

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(5)(i))
  if(i == 5)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//6

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt1(6)(i))
  if(i == 6)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  step(1)//7

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
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
  poke(c.io.sc2mac_wt.bits.mask(i),true)
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
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(0)(i))
  }
  step(1)//9

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(1)(i))
  if(i == 1)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(1)(i))

  }
  step(1)//10

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(2)(i))
  if(i == 2)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(2)(i))
  }
  
  step(1)//11

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(3)(i))
  if(i == 3)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(3)(i))
  }
  step(1)//12

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(4)(i))
  if(i == 4)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(4)(i))
  }
  step(1)//13

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(5)(i))
  if(i == 5)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(5)(i))
  }
  step(1)//14
                      //cacc st
  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(6)(i))
  if(i == 6)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(6)(i))
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000100000".U)
  step(1)//15

  poke(c.io.sc2mac_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_wt.bits.mask(i),true)
  poke(c.io.sc2mac_wt.bits.data(i),wt2(7)(i))
  if(i == 7)
  poke(c.io.sc2mac_wt.bits.sel(i),1)
  else
  poke(c.io.sc2mac_wt.bits.sel(i),0)
  }
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b001000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat1(7)(i))
  }
   expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//16
  
  poke(c.io.sc2mac_wt.valid,false)
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110100000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(0)(i))
  }
  expect(c.io.mac2accu.valid,true)
  expect(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//17
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(1)(i))
  }
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(0)(i))}
  step(1)//18
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(2)(i))
  }
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(1)(i))}
  step(1)//19
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(3)(i))
  }
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(2)(i))}
  step(1)//20
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(4)(i))
  }
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(3)(i))}
  step(1)//21
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(5)(i))
  }
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(4)(i))}
  step(1)//22
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b110000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(6)(i))
  }
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(5)(i))}
  step(1)//23
  
  poke(c.io.sc2mac_dat.valid,true)
  poke(c.io.sc2mac_dat.bits.pd,"b111000000".U)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.sc2mac_dat.bits.mask(i),true)
  poke(c.io.sc2mac_dat.bits.data(i),dat2(7)(i))
  }
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(6)(i))}
  step(1)//24
  
  poke(c.io.sc2mac_dat.valid,false)
  poke(c.io.sc2mac_dat.bits.pd,"b000000000".U)
  for(i <-0 to 7){
  expect(c.io.mac2accu.bits.mask(i),true)
  expect(c.io.mac2accu.bits.data(i),mout1(7)(i))}
  for(i <-0 to 7){
  step(1)
   for(j<-0 to 7){
  expect(c.io.mac2accu.bits.mask(j),true)
  expect(c.io.mac2accu.bits.data(j),mout2(i)(j))}}
  }
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
