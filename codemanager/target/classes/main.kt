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
    val json: ResponseTransformer = JsonTransformer()

    //filtagrem de Usuarios
    spark.post("/filtraUsuarios"){
        val rota  = FiltaUsuarios()

        json.render(rota.handle(request, response))
    }

    // fazer login
    spark.get("/verifica"){

        //val logado = verifica.handle(request, response) as Boolean


        val logado =  VerificaLogin().handle(request, response) as Boolean
        if (logado){

            "ok"
        }else{
            response.status(401)
            response.body("Usuario ou senha invalidos")
        }



    }
    //listagem de Usuarios
    spark.get("/listaUsuarios"){
        val rota: Route = ListaUsuarios()


        json.render(rota.handle(request, response))
    }

    spark.post("/criaUsuario"){
        CriaUsuario().handle(request, response)
    }
    spark.delete("/deletaUsuario/:id"){
        DeletaUsuario().handle(request,response)
    }



}

