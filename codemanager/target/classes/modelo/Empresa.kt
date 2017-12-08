package modelo

import java.util.*

class  Empresa(var nome: String?, var CNPJ: String?, var tipo: Tipo?, override var codigo: Int?): Modelo{
    constructor(): this(null, null, null, null)
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

enum class Tipo(var numero: Int){
    DESENVOLVEDOR(0),
    CLIENTE(1);
    companion object {
        fun Gera(num: Int) = Tipo.values().first { it.numero == num }
    }

}