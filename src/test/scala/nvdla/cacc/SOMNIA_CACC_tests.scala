package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_cacc_Tests (c:SOMNIA_cacc) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  poke(c.io.csb2cacc.req.valid,true)
  
  poke(c.io.somnia_core_rstn,true)//no reset
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000010000000000000000000001011".U)
  step(1)//truncate=8
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000011011".U)
  step(1)//bypass =1
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000000010".U)
  step(4)//reg2dp_op_en

  step(1)
  poke(c.io.mac2accu.valid,true)
  poke(c.io.mac2accu.bits.pd,"b000100000".U)//1
  step(1)
  poke(c.io.mac2accu.bits.pd,"b001000000".U)//2
  step(1)
  poke(c.io.mac2accu.bits.pd,"b110100000".U)//3
  step(1)
  poke(c.io.mac2accu.bits.pd,"b111000000".U)//4
  for(i <-0 to conf.CMAC_ATOMK-1){//1
  poke(c.io.mac2accu.bits.mask(i),true)
  if(i==0)
    poke(c.io.mac2accu.bits.data(i),1)
  else
    poke(c.io.mac2accu.bits.data(i),0)
  }
  step(1)
  poke(c.io.mac2accu.valid,false)
  for(i <-0 to conf.CMAC_ATOMK-1){//2
  poke(c.io.mac2accu.bits.mask(i),true)
  if(i==0)
    poke(c.io.mac2accu.bits.data(i),1)
  else
    poke(c.io.mac2accu.bits.data(i),0)
  }
  step(1)
  for(i <-0 to conf.CMAC_ATOMK-1){//3
  poke(c.io.mac2accu.bits.mask(i),true)
  if(i==0)
    poke(c.io.mac2accu.bits.data(i),"b0111111111111111111".U)
  else
    poke(c.io.mac2accu.bits.data(i),0)
  }
  step(1)
  for(i <-0 to conf.CMAC_ATOMK-1){//4
  poke(c.io.mac2accu.bits.mask(i),true)
  if(i==0)
    poke(c.io.mac2accu.bits.data(i),"b0111111111111111111".U)
  else
    poke(c.io.mac2accu.bits.data(i),0)
  }
  for(i <- 0 to 3){
  step(1)
      expect(c.io.cacc2ppu_pd.valid,false)
  }
  step(1)
  poke(c.io.cacc2ppu_pd.ready,true)
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000".U)
  step(1)
    expect(c.io.cacc2ppu_pd.valid,true)
      expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".U)
  step(1)
    expect(c.io.cacc2ppu_pd.valid,false)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000".U)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.cacc2ppu_pd.bits,"b1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".U)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,false)
  for(i<- 0 to 10){
  step(1)
    expect(c.io.cacc2ppu_pd.valid,false)
  }
}

class SOMNIA_cacc_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_cacc"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_cacc())(c => new SOMNIA_cacc_Tests(c)) should be (true)
    }
  }
}
