import org.eclipse.jetty.server.Authentication

import spark.kotlin.*
import modelo.Usuario
import persistencia.UsuarioDAO
import rotas.*
import spark.Request
import spark.Response
import spark.ResponseTransformer
import spark.Route

fun main(args: Array<String>) {

    val spark: Http = ignite()
    val json: ResponseTransformer = JsonTransformer()
    val rota: Route = ListaUsuarios()
    spark.get("/hello"){
        val user = Usuario()

        json.render(rota.handle(request, response))
    }


}