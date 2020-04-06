package nvdla

import chisel3._
import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner


object caccLauncher {
   implicit val conf: somniaConfig = new somniaConfig  
  val cacc = Map(
     "SOMNIA_CACC_assembly_buffer" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_assembly_buffer, manager) {
          (c) => new SOMNIA_CACC_assembly_bufferTests(c)
        }
      },
     "SOMNIA_CACC_assembly_ctrl" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_assembly_ctrl, manager) {
          (c) => new SOMNIA_CACC_assembly_ctrlTests(c)
        }
      },
     "SOMNIA_CACC_int8" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_CALC_int8, manager) {
          (c) => new SOMNIA_CACC_INT8_Tests(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("cacc", cacc, args)
  }
}

