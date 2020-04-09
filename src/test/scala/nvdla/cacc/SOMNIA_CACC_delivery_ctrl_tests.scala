package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CACC_delivery_ctrl_Tests(c:SOMNIA_CACC_delivery_ctrl) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  //1
  for(i <- 0 to conf.CACC_ATOMK-1){
  if(i == 0)
  poke(c.io.dlv_data(i),1)
  else
  poke(c.io.dlv_data(i),0)
  }
  poke(c.io.dlv_mask,true)
  poke(c.io.dlv_pd,0)
  poke(c.io.dlv_valid,true)
  step(1)
  //2
  for(i <- 0 to conf.CACC_ATOMK-1){
  if(i == 0)
  poke(c.io.dlv_data(i),1)
  else
  poke(c.io.dlv_data(i),0)
  }
  poke(c.io.dlv_mask,true)
  poke(c.io.dlv_pd,3)
  poke(c.io.dlv_valid,true)
  poke(c.io.dbuf_rd_ready,true)
  //1
  expect(c.io.dbuf_wr.addr.valid,true)
  expect(c.io.dbuf_wr.addr.bits,0)
  expect(c.io.dbuf_wr.data,1)
  step(1)
  poke(c.io.dlv_valid,false)
  //2
  expect(c.io.dbuf_wr.addr.valid,true)
  expect(c.io.dbuf_wr.addr.bits,1)
  expect(c.io.dbuf_wr.data,1)
  expect(c.io.dp2reg_done,true)
  //1
  expect(c.io.dbuf_rd_addr.valid,true)
  expect(c.io.dbuf_rd_addr.bits,0)
  step(1)
  //2
  expect(c.io.dbuf_rd_addr.valid,true)
  expect(c.io.dbuf_rd_addr.bits,1)
  expect(c.io.dbuf_rd_layer_end,true)
}

class SOMNIA_CACC_delivery_ctrl_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_delivery_ctrl"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_delivery_ctrl())(c => new SOMNIA_CACC_delivery_ctrl_Tests(c)) should be (true)
    }
  }
}
