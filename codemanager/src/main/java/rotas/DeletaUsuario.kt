package rotas

import modelo.Usuario
import persistencia.UsuarioDAO
import spark.Request
import spark.Response
import spark.Route

class DeletaUsuario: Route{
    override fun handle(p0: Request?, p1: Response?): Any {
        val user = Usuario()
        user.codigo = p0?.queryMap("codigo")?.value()?.toInt()
        println(user.codigo)
        return UsuarioDAO().remove(user)
    }
}