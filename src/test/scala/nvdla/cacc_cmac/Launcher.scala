package nvdla

import chisel3._
import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object convLauncher {
   implicit val conf: somniaConfig = new somniaConfig  
  val conv = Map(
   "SOMNIA_conv" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_convolution, manager) {
          (c) => new SOMNIA_convolution_Tests(c)
        }
      },
 )
  def main(args: Array[String]): Unit = {
    TutorialRunner("conv", conv, args)
  }
}
