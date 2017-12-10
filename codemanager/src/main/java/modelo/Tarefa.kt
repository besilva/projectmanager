package modelo

import java.time.LocalTime

class Tarefa(var nome: String, var descricao: String, var status: Status, override var codigo: Int?, override var horaAtualizacao: LocalTime): Modelo {
}