package rotas

import modelo.Usuario
import persistencia.UsuarioDAO
import spark.Request
import spark.Response
import spark.Route
import java.lang.Exception
import java.lang.IllegalArgumentException

class CriaUsuario:Route{
    override fun handle(p0: Request?, p1: Response?): Any {
        val user = Usuario()

        user.email = p0?.queryMap("email")?.value() ?: throw IllegalArgumentException("Faltou o email meu bom")
        user.senha = p0.queryMap("senha")?.value() ?: throw  IllegalArgumentException("Faltou colocar a senha brother")
        user.nome = p0.queryMap("nome")?.value() ?: throw IllegalArgumentException("Poderia informar seu nome please")
        user.codigo = p0.queryMap("codigo")?.value()?.toInt()
        user.empresa_codigo = p0.queryMap("empresa_codigo")?.value()?.toInt() ?: throw IllegalArgumentException("Tu nao trabalha vagabundo???")
        return  UsuarioDAO().persiste(user)

    }
}