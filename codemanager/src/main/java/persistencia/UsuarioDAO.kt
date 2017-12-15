package persistencia

import modelo.Usuario

import java.sql.PreparedStatement
import java.sql.ResultSet

import java.sql.Types


 class UsuarioDAO: AbstractDAO<Usuario>{
    constructor()


     override fun colunas(): String =  "nome, email, senha, empresa_codigo"

     override fun juncao(): String = " inner join Empresa e on e.codigo = usuario.empresa_codigo"
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
         when{
             objeto.empresa_codigo != null -> stmt.setInt(4,objeto.empresa_codigo!!)
             else ->  stmt.setNull(4, Types.VARCHAR)
         }
        if (objeto.isPersistente()){
            stmt.setInt(5, objeto.codigo!!)
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
             user.empresa_codigo = rs.getInt("empresa_codigo")
             lista.add(user)

         }
         return lista

     }

     override fun geraFiltro(exemplar : Usuario): String {
         var filtro : String = ""
         filtro += if (exemplar.nome != null)  " AND nome LIKE ? " else ""
         filtro += if (exemplar.email != null)  " AND email LIKE ? " else ""
         filtro += if (exemplar.senha != null)  " AND senha LIKE ? " else ""
         filtro += if (exemplar.empresa_codigo != null)  " AND empresa_codigo=? " else ""


        return filtro
     }

     override fun preencherFiltro(stmt: PreparedStatement, objeto: Usuario): PreparedStatement {
         var i = 1
         if (objeto.nome != null) stmt.setString(i++,"%"+objeto.nome+ "%")
         if (objeto.email != null) stmt.setString(i++,"%"+objeto.email+ "%")
         if (objeto.senha != null) stmt.setString(i++,"%"+objeto.senha+ "%")
         if (objeto.empresa_codigo != null) stmt.setInt(i++, objeto.empresa_codigo!!)
         return stmt
     }
     fun login(email: String, senha: String ): Boolean{
         val conexao = super.abreConexao()
         val sql = "Select * from usuario where email = ?  AND senha = ?"
         val stmt = conexao.prepareStatement(sql)
         stmt.setString(1,email )
         stmt.setString(2,senha )
         val rs = stmt.executeQuery()
         return rs.next()
     }
}