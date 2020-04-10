package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_cacc_Tests (c:SOMNIA_cacc) extends PeekPokeTester(c){
//  poke(c.io.csb2cacc.req.valid,true)
 // poke(c.io.cacc2ppu_pd.ready,true)
//  poke(c.io.somnia_core_rstn,true)//no reset
//  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000010000000000000000000001011".U)
 // step(1)//truncate=8
//  poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000011011".U)
 // step(1)//bypass =1
 // poke(c.io.csb2cacc.req.bits,"b000000001000000000000000000000000000000010000000000000000000010".U)
 // step(4)//reg2dp_op_en

 // poke(c.io.pwrbus_ram_pd,true)
 // poke(c.io.mac2accu.pd,"b000100000".U)//first mac2accu,last data 1,else 0 ,stripe st
 // for(i <- 0 to CMAC_ATOMK-1){
 // poke(c.io.mac2accu.mask,true)
 // if(i == 0)
 // poke(c.io.mac2accu.data,1)
 // else
 // poke(c.io.mac2accu.adta,0)
//  }
  
 // step(1)
 // poke(c.io.mac2accu.pd,"b001000000".U)//second mac2accu,last data 1,else 0 ,stripe end
 // for(i <- 0 to CMAC_ATOMK-1){
 // poke(c.io.mac2accu.mask,true)
 // if(i == 0)
 // poke(c.io.mac2accu.data,1)
 // else
 // poke(c.io.mac2accu.adta,0)
 // }
  
 // step(1)
 // poke(c.io.mac2accu.pd,"b000100000".U)//3 mac2accu,last data 1,else 0 ,stripe end
 // for(i <- 0 to CMAC_ATOMK-1){
 // poke(c.io.mac2accu.mask,true)
 // if(i == 0)
  //poke(c.io.mac2accu.data,1)
 // else
 // poke(c.io.mac2accu.adta,0)
 // }

 /// step(1)
 // poke(c.io.mac2accu.pd,"b111000000".U)//4 mac2accu,last data 1,else 0 ,stripe end
 // for(i <- 0 to CMAC_ATOMK-1){
  //poke(c.io.mac2accu.mask,true)
 // if(i == 0)
 // poke(c.io.mac2accu.data,1)
 // else
 // poke(c.io.mac2accu.adta,0)
 // }
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
