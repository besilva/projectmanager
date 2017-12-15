package modelo

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import javax.print.attribute.IntegerSyntax

interface Modelo{
        var codigo: Int?
        var horaAtualizacao: LocalDateTime
        fun isPersistente() = this.codigo != null


}