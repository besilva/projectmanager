package modelo

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class Etapa(var nome: String? = null, var criador: Usuario = Usuario(), var tarefas: ArrayList<Tarefa>? = ArrayList<Tarefa>(),var projeto: Projeto = Projeto(), var projeto_codigo: Int? = null, var usuario_codigo: Int? = null, override var codigo: Int? = null, override var horaAtualizacao: LocalDateTime = LocalDateTime.now()): Modelo {

}