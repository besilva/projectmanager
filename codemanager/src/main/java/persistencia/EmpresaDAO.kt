package persistencia

import modelo.Empresa
import modelo.Tipo
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

class EmpresaDAO() : AbstractDAO<Empresa>(){
    override fun colunas(): String = "nome"
    override fun getTabela(): String = "empresa"
    override fun juncao(): String = ""
    override fun geraFiltro(exemplar: Empresa): String {
        var filtro : String = ""
        filtro += if (exemplar.nome != null)  " AND nome LIKE ? " else ""
        filtro += if (exemplar.CNPJ != null)  " AND cnpj LIKE ? " else ""
        filtro += if (exemplar.tipo != null)  " AND tipo=? " else ""
        return  filtro
    }
    override fun preencherFiltro(stmt: PreparedStatement, objeto: Empresa): PreparedStatement {
        var i=1
        if (objeto.nome != null) stmt.setString(i++, "%" + objeto.nome + "%")
        if (objeto.CNPJ != null) stmt.setString(i++, "%" + objeto.CNPJ + "%")
        if (objeto.tipo != null) stmt.setInt(i++, objeto.tipo!!.numero)

        return stmt
    }

    override fun montaModelo(rs: ResultSet): MutableList<Empresa> {
        val lista = mutableListOf<Empresa>()
        while (rs.next()){
            var emp = Empresa()
            emp.nome = rs.getString("nome")
            emp.codigo = rs.getInt("codigo")
            emp.CNPJ = rs.getString("cnpj")
            emp.tipo = Tipo.Gera(rs.getInt("tipo"))
            lista.add(emp)
        }
        return  lista
    }

    override fun preencher(stmt: PreparedStatement, objeto: Empresa): PreparedStatement {

        when {
            objeto.nome != null -> stmt.setString(1, objeto.nome)
            else -> stmt.setNull(1, Types.VARCHAR)
        }
        when {
            objeto.CNPJ != null -> stmt.setString(2, objeto.CNPJ)
            else -> stmt.setNull(2, Types.VARCHAR)
        }
        when {
            objeto.tipo != null -> stmt.setInt(3, objeto.tipo!!.numero)
            else -> stmt.setNull(3, Types.VARCHAR)
        }
        if (objeto.isPersistente()){
            stmt.setInt(4, objeto.codigo!!)
        }
        return stmt
    }


}