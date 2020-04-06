package nvdla

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}


class SOMNIA_CMAC_CORE_rt_outTests(c: SOMNIA_CMAC_CORE_rt_out) extends PeekPokeTester(c) {
 
  implicit val conf: somniaConfig = new somniaConfig

  for (t <- 0 to 99) {
    //load random inputs
    val out_data = Array.fill(conf.CMAC_ATOMK){0}
    val out_mask = Array.fill(conf.CMAC_ATOMK){false}
    val out_pd = rnd.nextInt(1<<9)
    val out_pvld = rnd.nextBoolean()

    poke(c.io.out.bits.pd, out_pd)
    poke(c.io.out.valid, out_pvld)

    for (i <- 0 to conf.CMAC_ATOMK-1){

      out_data(i) = rnd.nextInt(1<<conf.CMAC_BPE)
      out_mask(i) = rnd.nextBoolean()

      poke(c.io.out.bits.data(i), out_data(i))
      poke(c.io.out.bits.mask(i), out_mask(i))

    }

    //after rt_out
    step(conf.CMAC_OUT_RT_LATENCY)

    //check the result
    //dat valid
    expect(c.io.mac2accu.valid, out_pvld)
    if(out_pvld){
      for (i <- 0 to conf.CMAC_ATOMK-1){
        //dat mask
        expect(c.io.mac2accu.bits.mask(i), out_mask(i))
        //dat data
        if(out_mask(i)){
          expect(c.io.mac2accu.bits.data(i), out_data(i))
        }}
      //dat pd
      expect(c.io.mac2accu.bits.pd, out_pd)
    }    
}}

class SOMNIA_CMAC_CORE_rt_outTester extends ChiselFlatSpec {

  behavior of "SOMNIA_CMAC_CORE_rt_out"
  backends foreach {backend =>
    it should s"correctly retiming wt and dat $backend" in {
      implicit val conf: somniaConfig = new somniaConfig
      Driver(() => new SOMNIA_CMAC_CORE_rt_out())(c => new SOMNIA_CMAC_CORE_rt_outTests(c)) should be (true)
    }
  }
}
