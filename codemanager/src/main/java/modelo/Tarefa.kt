package modelo

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Tarefa(var nome: String? = null, var descricao: String? = null, var etapa_codigo:Int? =null, override var codigo: Int?= null, override var horaAtualizacao: LocalDateTime = LocalDateTime.now()): Modelo {
    var status: Status
    init {
     status =Status.Criado
    }
}
