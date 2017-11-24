package persistencia

import modelo.Empresa
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

class EmpresaDAO() : AbstractDAO<Empresa>(){
    override fun colunas(): String = "nome"
    override fun getTabela(): String = "empresa"
    override fun geraFiltro(exemplar: Empresa): String {
        var filtro : String = ""
        filtro += if (exemplar.nome != null)  " AND nome LIKE ? " else ""
        return  filtro
    }
    override fun preencherFiltro(stmt: PreparedStatement, objeto: Empresa): PreparedStatement {
        var i = 1
        if (objeto.nome != null) stmt.setString(i++, "%" + objeto.nome + "%")
        return stmt
    }

    override fun montaModelo(rs: ResultSet): MutableList<Empresa> {
        val lista = mutableListOf<Empresa>()
        while (rs.next()){
            var emp = Empresa()
            emp.nome = rs.getString("nome")
            emp.codigo = rs.getInt("codigo")
            lista.add(emp)
        }
        return  lista
    }

    override fun preencher(stmt: PreparedStatement, objeto: Empresa): PreparedStatement {
        var i = 1
        when {
            objeto.nome != null -> stmt.setString(i++, objeto.nome)

            else -> stmt.setNull(1, Types.VARCHAR)
        }
        if (objeto.isPersistente()){
            stmt.setInt(i++, objeto.codigo!!)
        }
        return stmt
    }


}