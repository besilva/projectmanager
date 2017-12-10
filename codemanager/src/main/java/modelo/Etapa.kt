package modelo

import java.time.LocalTime


class Etapa(var nome: String?,projetoCodigo: Int, var usuario_codigo: Int, override var codigo: Int?, override var horaAtualizacao: LocalTime): Modelo {

}