package modelo

import jdk.net.SocketFlow
import java.time.LocalDateTime
import java.time.LocalTime

class Projeto(var nome: String? = null, var descricao: String? = null, var linguagem: Linguagem? = null, var usuarioCodigo: Int? = null,
              var empresaCodigo: Int? = null, var criador: Usuario? = null, var cliente: Empresa = Empresa(),
              override var codigo: Int? = null, override var horaAtualizacao: LocalDateTime = LocalDateTime.now(), var etapas: Array<Etapa> = emptyArray<Etapa>()) : Modelo{
    var status: Status

    init {
        status = Status.Iniciado

    }
}

enum class Linguagem {
    Kotlin,
    Python,
    Java,
    PHP,
    Ruby;

    companion object {
        fun Gera(num: Int) = Linguagem.values().first { it.ordinal == num }
    }

}
enum class Status {
    Criado,
    Iniciado,
    EmDesenvolvimento,
    Manutencao,
    Finalizado;

    companion object {
        fun Gera(num: Int) = Status.values().first { it.ordinal == num }
    }

}