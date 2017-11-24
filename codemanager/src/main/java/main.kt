import org.eclipse.jetty.server.Authentication

import spark.kotlin.*
import modelo.Usuario
import persistencia.UsuarioDAO
import rotas.*
import spark.*
import spark.staticfiles.StaticFilesFolder
import java.net.URL

fun main(args: Array<String>) {



    val spark: Http = ignite()


    // fazer login
    spark.post("/verifica"){
        val verifica  = VerificaLogin()
        val logado = verifica.handle(request, response) as Boolean
        if (logado){
            redirect("/index")
        }else{
            redirect("/login.html")
        }



    }
    spark.get("/hello"){
        val rota: Route = ListaUsuarios()
        val json: ResponseTransformer = JsonTransformer()

        json.render(rota.handle(request, response))
    }

    spark.get("index"){


    }
}
