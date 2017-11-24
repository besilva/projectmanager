package rotas

import spark.Request
import spark.Response
import spark.Route
import com.google.gson.Gson
import persistencia.UsuarioDAO


class VerificaLogin(): Route{
    override fun handle(p0: Request?, p1: Response?): Any {
        p1?.header("Access-Control-Allow-Origin", "*")
        val json = p0?.body() // pega o json da request
        println("post: " + json)
        val gson = Gson() // conversor
        return UsuarioDAO().login("","")
    }
}