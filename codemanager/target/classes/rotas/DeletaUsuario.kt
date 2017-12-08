package rotas

import modelo.Usuario
import persistencia.UsuarioDAO
import spark.Request
import spark.Response
import spark.Route

class DeletaUsuario: Route{
    override fun handle(p0: Request?, p1: Response?): Any {
        val user = Usuario()
        user.codigo = p0?.params(":id")?.toInt()

        return UsuarioDAO().remove(user)
    }
}