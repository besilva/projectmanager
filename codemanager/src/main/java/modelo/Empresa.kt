package modelo

import persistencia.UsuarioDAO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class  Empresa(var nome: String? = null, var CNPJ: String? = null, var email: String? = null, var senha: String? = null,
               var tipo: Tipo? = Tipo.DESENVOLVEDOR, override var codigo: Int? = null,
               override var horaAtualizacao: LocalDateTime = LocalDateTime.now()): Modelo{


    fun cadastraFuncionario(user: Usuario){
        user.empresa_codigo = this.codigo
        UsuarioDAO().persiste(user)
    }
    fun listaFuncionarios() = UsuarioDAO().seleciona(Usuario(empresa_codigo = this.codigo ))


}

enum class Tipo(){
    DESENVOLVEDOR(),
    CLIENTE();
    companion object {
        fun Gera(num: Int) = Tipo.values().first { it.ordinal == num }
    }

}