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
      },
     "SOMNIA_CACC_calculator" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_calculator, manager) {
          (c) => new SOMNIA_CACC_calculator_Tests(c)
        }
      },
     "SOMNIA_CACC_delivery_buffer" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_delivery_buffer, manager) {
          (c) => new SOMNIA_CACC_delivery_buffer_Tests(c)
        }
      },
     "SOMNIA_CACC_delivery_ctrl" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_delivery_ctrl, manager) {
          (c) => new SOMNIA_CACC_delivery_ctrl_Tests(c)
        }
      },
     "SOMNIA_CACC_dual_reg" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_dual_reg, manager) {
          (c) => new SOMNIA_CACC_dual_reg_Tests(c)
        }
      },
     "SOMNIA_CACC_reg" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CACC_regfile, manager) {
          (c) => new SOMNIA_CACC_regfile_Tests(c)
        }
      },
     "SOMNIA_cacc" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_cacc, manager) {
          (c) => new SOMNIA_cacc_Tests(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("cacc", cacc, args)
  }
}

