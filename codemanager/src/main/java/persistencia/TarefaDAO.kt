package persistencia

import modelo.Status
import modelo.Tarefa
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.sql.Types
import java.time.LocalDateTime
import java.time.LocalTime

class TarefaDAO: AbstractDAO<Tarefa>() {
    override fun colunas() = "nome, descricao, horaAtualizacao, status, etapa_codigo, codigo"
    override fun preencher(stmt: PreparedStatement, objeto: Tarefa): PreparedStatement {
        when{
            objeto.nome != null -> stmt.setString(1, objeto.nome)
            else -> stmt.setNull(1, Types.VARCHAR)
        }
        when{
            objeto.descricao != null -> stmt.setString(2, objeto.descricao)
            else -> stmt.setNull(2, Types.VARCHAR)
        }
        stmt.setTimestamp(3, toSqlTime(objeto.horaAtualizacao))
        stmt.setInt(4, objeto.status.ordinal)
        when {
            objeto.etapa_codigo != null ->stmt.setInt(5, objeto.etapa_codigo!!)
            else -> stmt.setNull(5, Types.INTEGER)
        }
                if(objeto.isPersistente()) stmt.setInt(6, objeto.codigo!!)


        return stmt
    }

    override fun getTabela() = " tarefa "
    override fun juncao() = ""
    override fun geraFiltro(exemplar: Tarefa): String {
        var filtro = ""
        if(!exemplar.nome.isNullOrEmpty()) filtro += " AND nome like ?"
        filtro += " AND status like ?"
        if (exemplar.descricao != null) filtro += " AND descricao like ?"
        if (exemplar.horaAtualizacao != LocalDateTime.now()) filtro +=" AND horaAtualizacao= ?"
        if (exemplar.codigo != null) filtro += " AND codigo = ?"
        if (exemplar.etapa_codigo != null) filtro += " AND etapa_codigo = ?"
        return filtro
    }

    override fun preencherFiltro(stmt: PreparedStatement, objeto: Tarefa): PreparedStatement {
        var i =1
        if (!objeto.nome.isNullOrEmpty()) stmt.setString(i++, objeto.nome)
        stmt.setInt(i++, objeto.status.ordinal)

        if(objeto.descricao != null) stmt.setString(i++, objeto.descricao)
        if(objeto.horaAtualizacao != LocalDateTime.now()) stmt.setTimestamp(i++, objeto.horaAtualizacao as Timestamp)
        if (objeto.isPersistente()) stmt.setInt(i++, objeto.codigo!!)
        if (objeto.etapa_codigo != null) stmt.setInt(i, objeto.etapa_codigo!!)
        return stmt
    }

    override fun montaModelo(rs: ResultSet): MutableList<Tarefa> {
        val lista = mutableListOf<Tarefa>()
        while (rs.next()){
            val tarefa = Tarefa()
            tarefa.nome = rs.getString("nome")
            tarefa.descricao = rs.getString("descricao")
            tarefa.status = Status.Gera(rs.getInt("status"))
            tarefa.horaAtualizacao = toLocalTime(rs.getTimestamp("horaAtualizacao"))
            tarefa.codigo = rs.getInt("codigo")
            tarefa.etapa_codigo = rs.getInt("etapa_codigo")
        }
        return lista
    }
}