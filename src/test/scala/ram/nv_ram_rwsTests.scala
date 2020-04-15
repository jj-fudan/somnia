package nvdla

import chisel3.util._
import chisel3.testers.BasicTester
import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}


class nv_ram_rwsTests(c: nv_ram_rws) extends PeekPokeTester(c) {

//for(t <- 0 until 100){ 
    //write a data
    val in_data = 1
    val in_address = 0

    poke(c.io.we, true)
    poke(c.io.wa, in_address)
    poke(c.io.di, in_data)

    step(1)
    poke(c.io.we, true)
    poke(c.io.wa, 1)
    poke(c.io.di, 2)
    poke(c.io.re, true)
    poke(c.io.ra, in_address)
    //expect(c.io.dout, in_data)
    step(1) 
    poke(c.io.we,false)
    poke(c.io.re, true)
    poke(c.io.ra, 1)
    expect(c.io.dout, 1)
    step(1)
    expect(c.io.dout,2)
}//}

class nv_ram_rwsTester extends ChiselFlatSpec {

  behavior of "nv_ram_rws"
  backends foreach {backend =>
    it should s"read and write$backend" in {
      Driver(() => new nv_ram_rws(64, 32))(c => new nv_ram_rwsTests(c)) should be (true)
    }
  }
}

