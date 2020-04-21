package nvdla

import chisel3._
import chisel3.iotesters._

object ConvMain extends App{
implicit val conf: somniaConfig = new somniaConfig
chisel3.iotesters.Driver.execute(args,()=>new SOMNIA_convolution)(c=>new SOMNIA_convolution_Tests2(c))
}
