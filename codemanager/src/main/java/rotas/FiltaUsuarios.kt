package rotas

import modelo.Usuario
import persistencia.UsuarioDAO
import spark.Request
import spark.Response
import spark.Route

class  FiltaUsuarios(): Route{
    override fun handle(p0: Request?, p1: Response?): Any {
        val user = Usuario()
        user.email = p0?.queryMap("email")?.value()
        user.senha = p0?.queryMap("senha")?.value()
        user.nome = p0?.queryMap("nome")?.value()
        return UsuarioDAO().seleciona(user)
    }
}