package modelo


import java.time.LocalTime
import java.util.*

class Usuario(var nome:String?, var senha:String?, var  email:String?, var empresa_codigo : Int?, var empresa: Empresa, override var codigo: Int?, override var horaAtualizacao: LocalTime): Modelo{

    constructor(): this(null, null, null, null, Empresa(), null, LocalTime.now())

}




