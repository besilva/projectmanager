package modelo

import jdk.net.SocketFlow
import java.time.LocalTime

class Projeto(var nome: String?, var descricao: String?, var linguagem: Linguagem?, var Status: Status?, var totalHoras:Int = 0, var usuarioCodigo: Int,
              var empresaCodigo: Int, var criador: Usuario, var cliente: Empresa,
              override var codigo: Int?,  override var horaAtualizacao: LocalTime) : Modelo{
}

enum class Linguagem {
    Kotlin,
    Python,
    Java,
    PHP,
    Ruby;

    companion object {
        fun Gera(num: Int) = Tipo.values().first { it.ordinal == num }
    }

}
enum class Status {
    Iniciado,
    EmDesenvolvimento,
    Manutencao,
    Finalizado;

    companion object {
        fun Gera(num: Int) = Tipo.values().first { it.ordinal == num }
    }

}