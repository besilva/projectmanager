package persistencia

import modelo.*
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.sql.Types
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ProjetoDAO: AbstractDAO<Projeto>() {
    override fun colunas(): String = "nome, descricao, linguagem, status, horaAtualizacao, usuario_codigo, empresa_codigo"
    override fun preencher(stmt: PreparedStatement, objeto: Projeto): PreparedStatement {
        when{
            objeto.nome != null -> stmt.setString(1, objeto.nome)
            else -> stmt.setNull(1, Types.VARCHAR)
        }
        when{
            objeto.descricao != null -> stmt.setString(2, objeto.descricao)
            else -> stmt.setNull(2, Types.VARCHAR)
        }
        when{
            objeto.linguagem != null -> stmt.setInt(3, objeto.linguagem!!.ordinal)
            else -> stmt.setNull(3, Types.INTEGER)
        }
        stmt.setInt(4, objeto.status.ordinal)
        stmt.setTimestamp(5, toSqlTime(objeto.horaAtualizacao))
        stmt.setInt(6, objeto.usuarioCodigo!!)


        stmt.setInt(7, objeto.empresaCodigo!!)
        if (objeto.isPersistente()) stmt.setInt(8, objeto.codigo!!)
        return stmt
    }
    override fun getTabela(): String = "projeto"

    override fun geraFiltro(exemplar: Projeto): String {
        var filtro = ""
        if(exemplar.nome != null) filtro += " AND nome like ?"
        if(exemplar.descricao != null) filtro += " AND descricao like ? "
        if(exemplar.linguagem != null) filtro += " AND linguagem=? "
        filtro += " AND status=? "
        //if(exemplar.horaAtualizacao != LocalDateTime.now()) filtro +=" AND horaAtualizacao=? "
        if(exemplar.usuarioCodigo != null) filtro += " AND usuario_codigo=? "
        if(exemplar.empresaCodigo != null) filtro += " AND empresa_codigo=? "
        if(exemplar.isPersistente()) filtro += " AND codigo=? "
        return filtro
    }


    override fun juncao(): String = "usuario, empresa"

    override fun montaModelo(rs: ResultSet): MutableList<Projeto> {
        var list = mutableListOf<Projeto>()
        while (rs.next()){
            var proj = Projeto()
            proj.nome = rs.getString("nome")
            proj.status = Status.Gera(rs.getInt("status"))
            proj.descricao = rs.getString("descricao")
            proj.linguagem = Linguagem.Gera(rs.getInt("linguagem"))
            proj.codigo = rs.getInt("codigo")
            proj.usuarioCodigo = rs.getInt("usuario_codigo")
            proj.empresaCodigo = rs.getInt("empresa_codigo")
            proj.criador = UsuarioDAO().seleciona(Usuario(codigo = proj.usuarioCodigo))[0]
            proj.cliente = EmpresaDAO().seleciona(Empresa(codigo = proj.empresaCodigo))[0]
            list.add(proj)
        }

        return list
    }

    override fun preencherFiltro(stmt: PreparedStatement, objeto: Projeto): PreparedStatement {
        var i = 1
        if(objeto.nome != null) stmt.setString(i++, objeto.nome)
        if(objeto.descricao != null) stmt.setString(i++, objeto.descricao)
        if(objeto.linguagem != null) stmt.setInt(i++, objeto.linguagem!!.ordinal)
        stmt.setInt(i++, objeto.status.ordinal)
        //if(objeto.horaAtualizacao != LocalDateTime.now()) stmt.setTimestamp(i++, toSqlTime(objeto.horaAtualizacao))
        if(objeto.usuarioCodigo != null) stmt.setInt(i++, objeto.usuarioCodigo!!)
        if(objeto.empresaCodigo != null) stmt.setInt(i++, objeto.empresaCodigo!!)
        if(objeto.isPersistente()) stmt.setInt(i, objeto.codigo!!)
        return stmt
    }


}