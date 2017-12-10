package modelo

import java.time.LocalTime
import java.util.*

class  Empresa(var nome: String?, var CNPJ: String?,var email: String?, var senha: String?, var tipo: Tipo?, override var codigo: Int?, override var horaAtualizacao: LocalTime): Modelo{
    constructor(): this(null, null, null, null, null, null, LocalTime.now())




}

enum class Tipo(){
    DESENVOLVEDOR(),
    CLIENTE();
    companion object {
        fun Gera(num: Int) = Tipo.values().first { it.ordinal == num }
    }

}