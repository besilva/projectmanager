package modelo


import java.util.*

class Usuario(var nome:String?, var senha:String?, var  email:String?, var empresa_codigo : Int?, var empresa: Empresa,  override var codigo: Int?): Modelo{

    constructor(): this(null, null, null, null, Empresa(), null)


    override fun getAtualizacao() {

    }

    override fun setAtualizacao(updated: Locale) {

    }

    override fun isPersistente(): Boolean {
        return super.isPersistente()
    }
}
