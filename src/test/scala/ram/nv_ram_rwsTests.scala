package nvdla

import chisel3.util._
import chisel3.testers.BasicTester
import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}


class nv_ram_rwsTests(c: nv_ram_rws) extends PeekPokeTester(c) {

//for(t <- 0 until 100){ 
    //write a data
    

    poke(c.io.we, true)
    poke(c.io.wa, 0)
    poke(c.io.di, 123456)

    step(1)
    poke(c.io.we, true)
    poke(c.io.wa, 1)
    poke(c.io.di, 21442)
    poke(c.io.re, true)
    poke(c.io.ra, 0)
    step(1) 
    poke(c.io.we,true)
    poke(c.io.wa, 2)
    poke(c.io.di, 124544)
    poke(c.io.re, true)
    poke(c.io.ra, 1)
    expect(c.io.dout, 123456)
    step(1)
    expect(c.io.dout,21442)
}//}

class nv_ram_rwsTester extends ChiselFlatSpec {

  behavior of "nv_ram_rws"
  backends foreach {backend =>
    it should s"read and write$backend" in {
      Driver(() => new nv_ram_rws(64, 32))(c => new nv_ram_rwsTests(c)) should be (true)
    }
  }
}

