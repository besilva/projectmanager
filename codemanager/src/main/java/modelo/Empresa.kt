package modelo

import java.util.*

class  Empresa(var nome: String?, override var codigo: Int?): Modelo{
    constructor(): this(null, null)
    override fun getAtualizacao() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAtualizacao(updated: Locale) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isPersistente(): Boolean {
        return super.isPersistente()
    }

}