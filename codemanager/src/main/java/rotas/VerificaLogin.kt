package rotas

import spark.Request
import spark.Response
import spark.Route
import com.google.gson.Gson
import persistencia.UsuarioDAO


class VerificaLogin(): Route{
    override fun handle(p0: Request?, p1: Response?): Any {

        p1?.header("Access-Control-Allow-Origin", "*")
        val email = p0?.queryMap("email")?.value()
        val senha = p0?.queryMap("senha")?.value()


        return UsuarioDAO().login(email!!,senha!!)


    }
}