package modelo


import persistencia.EtapaDAO
import persistencia.ProjetoDAO
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class Usuario(var nome:String? = null, var senha:String? = null, var  email:String? = null, var empresa_codigo : Int? = null, var empresa: Empresa = Empresa(), override var codigo: Int? = null, override var horaAtualizacao: LocalDateTime = LocalDateTime.now()): Modelo{

    fun criaProjeto(p: Projeto){
        p.usuarioCodigo = this.codigo
        p.empresaCodigo = this.empresa_codigo
        ProjetoDAO().persiste(p)
    }
    fun criaEtapa(e: Etapa, p: Projeto){
        e.criador = this
        e.usuario_codigo = this.codigo
        e.projeto_codigo = p.codigo
        EtapaDAO().persiste(e)
    }
    fun alteraProjeto(p: Projeto){
        if (p.isPersistente()) ProjetoDAO().persiste(p)
        else throw IllegalStateException("O projeto nao esta no banco")
    }



}




