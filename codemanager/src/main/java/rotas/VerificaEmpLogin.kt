package rotas

import persistencia.EmpresaDAO

import spark.Request
import spark.Response
import spark.Route

class VerificaEmpLogin : Route {
    override fun handle(p0: Request?, p1: Response?): Any {

        p1?.header("Access-Control-Allow-Origin", "*")
        val email = p0?.queryMap("email")?.value()
        val senha = p0?.queryMap("senha")?.value()


        return EmpresaDAO().login(email!!,senha!!)


    }
}