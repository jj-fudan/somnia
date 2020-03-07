class csc2cmac_wt_if(implicit val conf: simbaConfig) extends Bundle{
    val sel = Output(Vec(conf.CMAC_ATOMK, Bool()))
    val mask = Output(Vec(conf.CMAC_ATOMC, Bool()))
    val data = Output(Vec(conf.CMAC_ATOMC, UInt(conf.CMAC_BPE.W)))
}
class reg_control_if extends Bundle{
    val rd_data = Output(UInt(32.W))
    val offset = Input(UInt(12.W))
    val wr_data = Input(UInt(32.W))
    val wr_en = Input(Bool())
}
