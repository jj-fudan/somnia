package nvdla

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}


class SOMNIA_CMAC_CORE_activeTests(c: SOMNIA_CMAC_CORE_active) extends PeekPokeTester(c) {
 
  implicit val conf: somniaConfig = new somniaConfig
  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0){
  poke(c.io.in_wt.bits.data(i),1)
  poke(c.io.in_wt.bits.sel(i),1)}
  else{
  poke(c.io.in_wt.bits.data(i),0) 
  poke(c.io.in_wt.bits.sel(i),0)}
  }
  step(1)
  
  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),2)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 1)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),3)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 2)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),4)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 3)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  step(1)
  
  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),5)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 4)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),6)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 5)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),7)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i ==6)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  step(1)
   
  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),8)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 7)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }//wt1 ok
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0){
  poke(c.io.in_wt.bits.data(i),9)
  poke(c.io.in_wt.bits.sel(i),1)}
  else{
  poke(c.io.in_wt.bits.data(i),0) 
  poke(c.io.in_wt.bits.sel(i),0)}
  }//wt2 st
  //dat1 st
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,true)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),10)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 1)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  for(i <- 0 to conf.CMAC_ATOMK-1)
     for(j <- 0 to conf.CMAC_ATOMK-1){
       expect(c.io.dat_actv(i)(j).valid,true)
       expect(c.io.wt_actv(i)(j).valid,true)
       expect(c.io.dat_actv(i)(j).bits.nz,true)
       expect(c.io.wt_actv(i)(j).bits.nz,true)
       if(j==0){
          expect(c.io.dat_actv(i)(j).bits.data,1)
          expect(c.io.wt_actv(i)(j).bits.data,i+1)
          }
     }
  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),11)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 2)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),12)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 3)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)
  
  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),13)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 4)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),14)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 5)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),15)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i ==6)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)
   
  poke(c.io.in_wt.valid,true)
  for( i <- 0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_wt.bits.mask(i),true)
  if(i == 0)
  poke(c.io.in_wt.bits.data(i),16)
  else
  poke(c.io.in_wt.bits.data(i),0) 
  if(i == 7)
  poke(c.io.in_wt.bits.sel(i),1)
  else
  poke(c.io.in_wt.bits.sel(i),0)
  }
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,true)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)
  
  poke(c.io.in_wt.valid,false)//wt2 ok 
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,true)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

   for(i <- 0 to conf.CMAC_ATOMK-1)
     for(j <- 0 to conf.CMAC_ATOMK-1){
       expect(c.io.dat_actv(i)(j).valid,true)
       expect(c.io.wt_actv(i)(j).valid,true)
       expect(c.io.dat_actv(i)(j).bits.nz,true)
       expect(c.io.wt_actv(i)(j).bits.nz,true)
       if(j==0){
          expect(c.io.dat_actv(i)(j).bits.data,1)
          expect(c.io.wt_actv(i)(j).bits.data,i+9)
          }
     }
 
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)
  
  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)

  poke(c.io.in_dat.valid,true)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,true)
  for(i<-0 to conf.CMAC_ATOMK-1){
  poke(c.io.in_dat.bits.mask(i),true)
  if(i==0)
  poke(c.io.in_dat.bits.data(i),1)
  else
  poke(c.io.in_dat.bits.data(i),0)
  }
  step(1)
  poke(c.io.in_dat.valid,false)
  poke(c.io.in_dat_stripe_st,false)
  poke(c.io.in_dat_stripe_end,false)
  step(1)
  for(i <- 0 to conf.CMAC_ATOMK-1)
     for(j <- 0 to conf.CMAC_ATOMK-1){
       expect(c.io.dat_actv(i)(j).valid,true)
       expect(c.io.wt_actv(i)(j).valid,true)
       expect(c.io.dat_actv(i)(j).bits.nz,true)
       expect(c.io.wt_actv(i)(j).bits.nz,true)
       if(j==0){
          expect(c.io.dat_actv(i)(j).bits.data,1)
          expect(c.io.wt_actv(i)(j).bits.data,i+9)
          }
     }
  step(1)
   for(i <- 0 to conf.CMAC_ATOMK-1){
        for (j <- 0 to conf.CMAC_ATOMC-1){
        expect(c.io.dat_actv(i)(j).valid,false)
        expect(c.io.wt_actv(i)(j).valid,false)
        }
   }
}

class NV_NVDLA_CMAC_CORE_activeTester extends ChiselFlatSpec {

  behavior of "SOMNIA_CMAC_CORE_active"
  backends foreach {backend =>
    it should s"correctly activate wt and dat $backend" in {
      implicit val nvconf: somniaConfig = new somniaConfig
      Driver(() => new SOMNIA_CMAC_CORE_active())(c => new SOMNIA_CMAC_CORE_activeTests(c)) should be (true)
    }
  }
}
