package nvdla

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.util._

class SOMNIA_CACC_assembly_ctrlTests (c:SOMNIA_CACC_assembly_ctrl) extends PeekPokeTester(c){
    //layer st
    //mac2accu
    val mac2accu_pd = "b000100000".U
    

    poke(c.io.mac2accu_pd.valid,true)
    poke(c.io.mac2accu_pd.bits,mac2accu_pd)
    poke(c.io.reg2dp_op_en,true)
    poke(c.io.reg2dp_clip_truncate,"b00010".U)
    poke(c.io.reg2dp_relu_bypass,true)
    poke(c.io.dp2reg_done,false)
    step(2)
    expect(c.io.wait_for_op_en,false)
    expect(c.io.cfg_truncate,"b00010".U)
    expect(c.io.cfg_relu_bypass,true)
    expect(c.io.cfg_in_en_mask,true)
    expect(c.io.abuf_rd_addr.valid,false)
    expect(c.io.abuf_rd_addr.bits,0)
    expect(c.io.accu_ctrl_pd.valid,true)
    expect(c.io.accu_ctrl_ram_valid,false)
    expect(c.io.accu_ctrl_pd.bits,"b0000001000000".U)
}


class SOMNIA_CACC_assembly_ctrl_Tester extends ChiselFlatSpec{
  behavior of "SOMNIA_CACC_assembly_ctrl"
  backends foreach{ backend =>
     it should s"correctly $backend" in{
        implicit val conf: somniaConfig = new somniaConfig
         Driver(() => new SOMNIA_CACC_assembly_ctrl())(c => new SOMNIA_CACC_assembly_ctrlTests(c)) should be (true)
    }
  }
}
