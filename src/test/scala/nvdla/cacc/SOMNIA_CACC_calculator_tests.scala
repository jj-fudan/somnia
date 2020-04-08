package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CACC_calculator_Tests (c:SOMNIA_CACC_calculator) extends PeekPokeTester(c){
  implicit val conf: somniaConfig = new somniaConfig
  val abuf_data1 = "b01111111111111111111111111111111110000000000000000000000000000000001000000000000000000000000000000000100000000000000000000000000000000010000000000000000000000000000000001000000000000000000000000000000000100000000000000000000000000000000010000000000000000000000000000000001".U
                   //0000000000000000000000000000000010"
  val ctrl_pd = "b0000001000010".U
  val ram_valid = true
  val mac2accudata= Array.fill(conf.CACC_ATOMK){1}
  val mac2accumask = Array.fill(conf.CACC_ATOMK){true}
  poke(c.io.mac2accu_pvld,true)
  for(i<- 0 to conf.CACC_ATOMK-1){
  poke(c.io.mac2accu_data(i),mac2accudata(i))
  poke(c.io.mac2accu_mask(i),mac2accumask(i))}
  step(2)
  poke(c.io.accu_ctrl_pd.bits,ctrl_pd)
  poke(c.io.accu_ctrl_pd.valid,true)
  step(1)
  //2
  poke(c.io.accu_ctrl_pd.bits,"b0010001000010".U)
  //1
  poke(c.io.accu_ctrl_ram_valid,true)
  poke(c.io.abuf_rd_data,abuf_data1)
  poke(c.io.cfg_in_en_mask,true)
  poke(c.io.cfg_truncate,8)
  step(3)
  //first out
  expect(c.io.abuf_wr.addr.valid,true)
  expect(c.io.abuf_wr.addr.bits,2)
  expect(c.io.abuf_wr.data,"b01111111111111111111111111111111110000000000000000000000000000000010000000000000000000000000000000001000000000000000000000000000000000100000000000000000000000000000000010000000000000000000000000000000001000000000000000000000000000000000100000000000000000000000000000000010".U)
  expect(c.io.dlv_valid,false)
  expect(c.io.dp2reg_sat_count,0)
  step(10)
  //2 out
  expect(c.io.abuf_wr.addr.valid,false)
  //expect(c.io.dlv_data,"b0000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".U)
  for(i<-0 to conf.CACC_ATOMK-1){
  if(i == 7 )
     expect(c.io.dlv_data(i),"b00000010000000000000000000000000".U)   
  else
     expect(c.io.dlv_data(i),"b00000000000000000000000000000000".U)   
  }                     
  expect(c.io.dlv_valid,true)
  expect(c.io.dp2reg_sat_count,0)
  expect(c.io.dlv_mask,true)
  expect(c.io.dlv_pd,0)
}

class SOMNIA_CACC_calculator_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_calculator"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_calculator())(c => new SOMNIA_CACC_calculator_Tests(c)) should be (true)
    }
  }
}
