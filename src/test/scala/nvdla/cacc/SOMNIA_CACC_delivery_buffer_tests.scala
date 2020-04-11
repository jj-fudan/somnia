package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CACC_delivery_buffer_Tests (c:SOMNIA_CACC_delivery_buffer)extends PeekPokeTester(c){
  val dbuf_wr_addr = 0
  val dbuf_wr_data = 1
  val dbuf_rd_addr = 0
  poke(c.io.dbuf_wr.addr.valid,true)
  poke(c.io.dbuf_wr.addr.bits,dbuf_wr_addr)
  poke(c.io.dbuf_wr.data,dbuf_wr_data)
  step(1)
  poke(c.io.dbuf_rd_addr.bits,dbuf_rd_addr)
  poke(c.io.dbuf_wr.addr.valid,false)
  poke(c.io.dbuf_rd_layer_end,false)
  poke(c.io.dbuf_rd_addr.valid,true)
  poke(c.io.cacc2ppu_pd.ready,true)//dbuf_rd_en_new
  step(1)
  poke(c.io.dbuf_rd_addr.valid,false)
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.dbuf_rd_ready,false)
  expect(c.io.cacc2ppu_pd.bits,1)
  expect(c.io.cacc2glb_done_intr_pd,0)
  expect(c.io.accu2sc_credit_size.bits,1)
  expect(c.io.accu2sc_credit_size.valid,false)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.dbuf_rd_ready,false)
  expect(c.io.cacc2ppu_pd.bits,0)
  expect(c.io.cacc2glb_done_intr_pd,0)
  expect(c.io.accu2sc_credit_size.bits,1)
  expect(c.io.accu2sc_credit_size.valid,false)
  poke(c.io.dbuf_wr.addr.valid,true)
  poke(c.io.dbuf_wr.addr.bits,1)
  poke(c.io.dbuf_wr.data,2)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,false)
  expect(c.io.dbuf_rd_ready,true)
  expect(c.io.cacc2ppu_pd.bits,1)
  expect(c.io.cacc2glb_done_intr_pd,0)
  expect(c.io.accu2sc_credit_size.bits,1)
  expect(c.io.accu2sc_credit_size.valid,true)
//  step(1)
  
//  step(1)
  poke(c.io.dbuf_rd_addr.bits,1)
  poke(c.io.dbuf_wr.addr.valid,false)
  poke(c.io.dbuf_rd_layer_end,true)
  poke(c.io.dbuf_rd_addr.valid,true)
  poke(c.io.cacc2ppu_pd.ready,true)//dbuf_rd_en_new
  step(1)
  poke(c.io.dbuf_rd_addr.valid,false)
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.dbuf_rd_ready,false)
  expect(c.io.cacc2ppu_pd.bits,2)
  expect(c.io.cacc2glb_done_intr_pd,0)
  expect(c.io.accu2sc_credit_size.bits,1)
  expect(c.io.accu2sc_credit_size.valid,false)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,true)
  expect(c.io.dbuf_rd_ready,false)
  //680564733841876926926749214863536422912=2^130
  expect(c.io.cacc2ppu_pd.bits,"b1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".U)
  expect(c.io.cacc2glb_done_intr_pd,0)
  expect(c.io.accu2sc_credit_size.bits,1)
  expect(c.io.accu2sc_credit_size.valid,false)
  step(1)
  expect(c.io.cacc2ppu_pd.valid,false)
  expect(c.io.dbuf_rd_ready,true)
  expect(c.io.cacc2ppu_pd.bits,2)
  expect(c.io.cacc2glb_done_intr_pd,1)
  expect(c.io.accu2sc_credit_size.bits,1)
  expect(c.io.accu2sc_credit_size.valid,true)
  
}


class SOMNIA_CACC_delivery_buffer_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_delivery_buffer"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_delivery_buffer())(c => new SOMNIA_CACC_delivery_buffer_Tests(c)) should be (true)
    }
  }
}
