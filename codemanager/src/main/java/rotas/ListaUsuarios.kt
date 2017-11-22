package rotas

import modelo.Usuario
import persistencia.UsuarioDAO
import spark.Request
import spark.Response
import spark.Route

class ListaUsuarios() : Route{
    override fun handle(p0: Request?, p1: Response?): Any {
        p1?.header("Content-Type", "application/json") // mime-type
        p1?.header("Access-Control-Allow-Origin", "*")

        return UsuarioDAO().seleciona()

    }
}