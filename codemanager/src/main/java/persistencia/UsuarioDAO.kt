package persistencia

import modelo.Usuario
import java.lang.reflect.Type
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLType
import java.sql.Statement
import java.sql.Types
import java.util.List

 class UsuarioDAO: AbstractDAO<Usuario>{
    constructor()

     override fun colunas(): String =  "nome, email, senha"



     override fun getTabela(): String = "usuario"


     override fun preencher(stmt: PreparedStatement, objeto: Usuario) : PreparedStatement {
        when {
            objeto.nome != null -> stmt.setString(1, objeto.nome)
            else -> stmt.setNull(1, Types.VARCHAR)
        }
        when {
            objeto.email != null -> stmt.setString(2, objeto.email )
            else ->   stmt.setNull(2, Types.VARCHAR)
        }
        when{
            objeto.senha != null -> stmt.setString(3,objeto.senha)
            else ->  stmt.setNull(3, Types.VARCHAR)
        }
        if (objeto.isPersistente()){
            stmt.setInt(4, objeto.codigo!!)
        }
        return stmt

     }

     override fun montaModelo(rs: ResultSet):MutableList<Usuario> {
         val lista = mutableListOf<Usuario>()
         while (rs.next()){
             var user = Usuario()
             user.nome = rs.getString("nome")
             user.email = rs.getString("email")
             user.senha = rs.getString("senha")
             user.codigo = rs.getInt("codigo")
             lista.add(user)

         }
         return lista
     }

     override fun geraFiltro(exemplar : Usuario): String {
         var filtro : String = ""
         filtro += if (exemplar.nome != null)  " AND nome LIKE %" + exemplar.nome + "% " else ""
         filtro += if (exemplar.email != null)  " AND email LIKE %" + exemplar.nome + "% " else ""
         filtro += if (exemplar.senha != null)  " AND senha LIKE %" + exemplar.nome + "% " else ""
        return filtro
     }

}