package modelo


import java.util.*

class Usuario(var nome:String?, var senha:String?, var  email:String?,override var codigo: Int?): Modelo{

    constructor(): this(null, null, null, null)


    override fun getAtualizacao() {

    }

    override fun setAtualizacao(updated: Locale) {

    }

    override fun isPersistente(): Boolean {
        return super.isPersistente()
    }
}
