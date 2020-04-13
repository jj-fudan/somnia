// See LICENSE.txt for license details.
package nvdla

import chisel3._
import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner


object cmacLauncher{  
  implicit val conf: somniaConfig = new somniaConfig
  val cmac = Map(
      "SOMNIA_CMAC_CORE_mac" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_CORE_mac(), manager) {
          (c) => new SOMNIA_CMAC_CORE_macTests(c)
        }
      },
	"SOMNIA_CMAC_CORE_activate" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_CORE_active(), manager) {
          (c) => new SOMNIA_CMAC_CORE_activateTest(c)
        }
      },
      "SOMNIA_CMAC_CORE_active" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_CORE_active(), manager) {
          (c) => new SOMNIA_CMAC_CORE_activeTests(c)
        }
      },
	"SOMNIA_CMAC_REG_single" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new NV_NVDLA_BASIC_REG_single(), manager) {
          (c) => new SOMNIA_CMAC_REG_single_Tests(c)
        }
      },
      "SOMNIA_CMAC_CORE_rt_in" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_CORE_rt_in(), manager) {
          (c) => new SOMNIA_CMAC_CORE_rt_inTests(c)
        }
      },
      "SOMNIA_CMAC_CORE_rt_out" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_CORE_rt_out(), manager) {
          (c) => new SOMNIA_CMAC_CORE_rt_outTests(c)
        }
      },      
   	"SOMNIA_CMAC_REG_dual" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_REG_dual(), manager) {
          (c) => new SOMNIA_CMAC_REG_dual_Tests(c)
        }
      }, 
	"SOMNIA_CMAC_core" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_core(), manager) {
          (c) => new SOMNIA_CMAC_CORE_Tests(c)
        }
      },
"SOMNIA_CMAC_core2" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_core(), manager) {
          (c) => new SOMNIA_CMAC_CORE_Testss(c)
        }
      },
        "SOMNIA_CMAC_reg" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_CMAC_reg(), manager) {
          (c) => new SOMNIA_CMAC_REG_Tests(c)
        }
      },
        "SOMNIA_CSB" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new NV_NVDLA_CSB_LOGIC(), manager) {
          (c) => new SOMNIA_CSB_Tests(c)
        }
      },
        "SOMNIA_cmac" -> { 
        (manager: TesterOptionsManager) =>
        Driver.execute(() => new SOMNIA_cmac(), manager) {
          (c) => new SOMNIA_CMAC_Tests(c)
        }
      },
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("cmac", cmac, args)
  }
}

