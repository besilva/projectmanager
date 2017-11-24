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
        var i = 1
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
         filtro += if (exemplar.nome != null)  " AND nome LIKE ? " else ""
         filtro += if (exemplar.email != null)  " AND email LIKE ? " else ""
         filtro += if (exemplar.senha != null)  " AND senha LIKE ? " else ""

        return filtro
     }

     override fun preencherFiltro(stmt: PreparedStatement, objeto: Usuario): PreparedStatement {
         var i = 1
         if (objeto.nome != null) stmt.setString(i++,"%"+objeto.nome+ "%")
         println(i)
         if (objeto.email != null) stmt.setString(i++,"%"+objeto.email+ "%")
         if (objeto.senha != null) stmt.setString(i++,"%"+objeto.senha+ "%")
         return stmt
     }
     fun login(email: String?, senha: String? ): Boolean{
         val conexao = super.abreConexao()
         val sql = "Select * from usuario where email LIKE ? AND senha LIKE ?"
         val stmt = conexao.prepareStatement(sql)
         when{
            email != null -> stmt.setString(1,email )
             else -> return false
         }
         when{
             senha != null -> stmt.setString(1,senha )
             else -> return false
         }
         val rs = stmt.executeQuery()
         return rs.next()
     }
}