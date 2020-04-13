package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_cacc_Tests (c:SOMNIA_cacc) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  poke(c.io.csb2cacc.req.valid,true)
  poke(c.io.cacc2ppu_pd.ready,true)
  poke(c.io.somnia_core_rstn,true)//no reset
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000000000000000000000001011".U)
  step(1)//truncate=0
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000011011".U)
  step(1)//bypass =1
  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000000010".U)
  step(10)//reg2dp_op_en

  step(1)//1
  poke(c.io.mac2accu.valid,true)
  poke(c.io.mac2accu.bits.pd,"b000100000".U)
  step(1)//2
  poke(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//3
  poke(c.io.mac2accu.bits.pd,"b000000000".U)
  step(1)//4
  poke(c.io.mac2accu.bits.pd,"b000000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//5
  poke(c.io.mac2accu.bits.pd,"b000000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//6
  poke(c.io.mac2accu.bits.pd,"b000000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//7
  poke(c.io.mac2accu.bits.pd,"b000000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//8
  poke(c.io.mac2accu.bits.pd,"b001000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//9
  poke(c.io.mac2accu.bits.pd,"b110100000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//10
  poke(c.io.mac2accu.bits.pd,"b110000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//11
  poke(c.io.mac2accu.bits.pd,"b110000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+1)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//12
  poke(c.io.mac2accu.bits.pd,"b110000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//13
  poke(c.io.mac2accu.bits.pd,"b110000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//14
  poke(c.io.mac2accu.bits.pd,"b110000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//15
  poke(c.io.mac2accu.bits.pd,"b110000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//16
  poke(c.io.mac2accu.bits.pd,"b111000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//17
  poke(c.io.mac2accu.valid,false)
  poke(c.io.mac2accu.bits.pd,"b000000000".U)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  step(1)//18
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  //00000000000000000000000000010010
  expect(c.io.cacc2ppu_pd.valid,true)//out1
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000010000000000000000000000000000000011100000000000000000000000000000110000000000000000000000000000001010".U)
  step(1)//19
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),true)
  }
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000011000000000000000000000000000000101100000000000000000000000000001010000000000000000000000000000010010".U)
  step(1)//20
  expect(c.io.cacc2ppu_pd.valid,false)
  for(i <-0 to conf.CMAC_ATOMK-1){
  poke(c.io.mac2accu.bits.data(i),i+9)
  poke(c.io.mac2accu.bits.mask(i),false)
  }
  step(1)//21
  expect(c.io.cacc2ppu_pd.valid,true)//out2
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000010000000000000000000000000000000011100000000000000000000000000000110000000000000000000000000000001010".U)
  step(1)//22
expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000011000000000000000000000000000000101100000000000000000000000000001010000000000000000000000000000010010".U)
  step(1)//23
  expect(c.io.cacc2ppu_pd.valid,false)
  for(i<-0 to 4){
  step(1)//21
  expect(c.io.cacc2ppu_pd.valid,true)//out2
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000010000000000000000000000000000000011100000000000000000000000000000110000000000000000000000000000001010".U)
  step(1)//22
expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000011000000000000000000000000000000101100000000000000000000000000001010000000000000000000000000000010010".U)
  step(1)//23
  expect(c.io.cacc2ppu_pd.valid,false)
  }
  step(1)
  expect(c.io.cacc2ppu_pd.valid,true)//out3
  expect(c.io.cacc2ppu_pd.bits,"b0000000000000000000000000000010000000000000000000000000000000011100000000000000000000000000000110000000000000000000000000000001010".U)
  step(1)
expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.cacc2ppu_pd.bits,"b1000000000000000000000000000011000000000000000000000000000000101100000000000000000000000000001010000000000000000000000000000010010".U)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,false)
  
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
