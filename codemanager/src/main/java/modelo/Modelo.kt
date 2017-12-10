package modelo

import java.time.LocalTime
import java.util.*
import javax.print.attribute.IntegerSyntax

interface Modelo{
        var codigo: Int?
        var horaAtualizacao: LocalTime
        fun isPersistente() = this.codigo != null


}