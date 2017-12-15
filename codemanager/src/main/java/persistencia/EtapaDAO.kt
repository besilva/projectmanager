package persistencia

import modelo.Etapa
import modelo.Projeto
import modelo.Usuario
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.sql.Types
import java.time.LocalDateTime
import java.time.LocalTime

class EtapaDAO: AbstractDAO<Etapa>() {
    override fun colunas() = "nome, horaAtualizacao, usuario_codigo, projeto_codigo"
    override fun preencher(stmt: PreparedStatement, objeto: Etapa): PreparedStatement {
        when{
            objeto.nome != null  -> stmt.setString(1, objeto.nome)
            else -> stmt.setNull(1, Types.VARCHAR)
        }

        stmt.setTimestamp(2, toSqlTime(objeto.horaAtualizacao))
        stmt.setInt(3, objeto.usuario_codigo!!)
        stmt.setInt(4, objeto.projeto_codigo!!)

        return  stmt



    }

    override fun getTabela() = "etapa"
    override fun geraFiltro(exemplar: Etapa): String {
        var filtro = ""
        if (!exemplar.nome.isNullOrEmpty()) filtro += " AND nome like ?"
        if (exemplar.horaAtualizacao != LocalDateTime.now()) filtro += "AND horaAtualizacao = ? "
        if (exemplar.projeto_codigo != null) filtro += " AND projeto_codigo = ?"
        if (exemplar.usuario_codigo != null) filtro += " AND usuario_codigo = ?"
        return filtro
    }

    override fun preencherFiltro(stmt: PreparedStatement, objeto: Etapa): PreparedStatement {
        var i = 1
        if(objeto.nome != null) stmt.setString(i++, objeto.nome)
        if(objeto.horaAtualizacao != LocalDateTime.now()) stmt.setTimestamp(i++, toSqlTime(objeto.horaAtualizacao))
        if(objeto.projeto_codigo != null) stmt.setInt(i++, objeto.usuario_codigo!!)
        if(objeto.usuario_codigo != null) stmt.setInt(i++, objeto.projeto_codigo!!)
        return stmt
    }

    override fun montaModelo(rs: ResultSet): MutableList<Etapa> {
        val lista = mutableListOf<Etapa>()
        while (rs.next()){
            val etapa = Etapa()
            etapa.nome = rs.getString("nome")
            etapa.horaAtualizacao = toLocalTime(rs.getTimestamp("horaAtualizacao"))
            etapa.projeto_codigo = rs.getInt("projeto_codigo")
            etapa.usuario_codigo = rs.getInt("usuario_codigo")
            if(etapa.projeto_codigo!! >  0 ) etapa.projeto = ProjetoDAO().seleciona(Projeto(codigo = etapa.projeto_codigo)).first()
            if(etapa.usuario_codigo!! > 0) etapa.criador = UsuarioDAO().seleciona(Usuario(codigo = etapa.usuario_codigo)).first()
            lista.add(etapa)
        }
        return lista
    }

    override fun juncao() = ""
}