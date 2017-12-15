import modelo.*
import org.eclipse.jetty.server.Authentication

import spark.kotlin.*
import persistencia.EmpresaDAO
import persistencia.EtapaDAO
import persistencia.ProjetoDAO
import persistencia.UsuarioDAO
import rotas.*

import spark.*
import spark.staticfiles.StaticFilesFolder
import java.net.URL

fun main(args: Array<String>) {
    var empresa = Empresa("Backends Maneiros", "1111111", "teste@teste", "1234")
    var marcio = Usuario("Marcio Torres", "123", "m@torres.com")
    val emp = EmpresaDAO()
    val user = UsuarioDAO()
    emp.persiste(empresa)
    empresa.cadastraFuncionario(marcio)
    val lista = empresa.listaFuncionarios()
    var projetao = Projeto("Transformar pessoas que não viram Rick e Morty em programadores", "Este projeto vai ser dificil", Linguagem.Java)
    marcio.criaProjeto(projetao)
    val lista2 = emp.seleciona()
    val proj = ProjetoDAO()
    val etapa = Etapa("Desespero")
    marcio.criaEtapa(etapa, projetao)


    println(lista.size == 1)
    println(lista.get(0).nome.equals("Marcio Torres"))
    println(lista2.get(0).nome.equals("Backends Maneiros"))
    println(proj.seleciona(projetao).size == 1)
    println(proj.seleciona(0,1).first().nome.equals("Transformar pessoas que não viram Rick e Morty em programadores"))
    println(EtapaDAO().seleciona().get(0).nome.equals("Desespero"))
    projetao.status = Status.Finalizado
    marcio.alteraProjeto(projetao)
    println(ProjetoDAO().seleciona().get(0).status == Status.Finalizado)
/*(
    val spark: Http = ignite()
    val json: ResponseTransformer = JsonTransformer()

    //filtagrem de Usuarios
    spark.post("/filtraUsuarios"){
        val rota  = FiltaUsuarios()

        json.render(rota.handle(request, response))
    }

    // fazer login
    spark.post("/verifica"){

        //val logado = verifica.handle(request, response) as Boolean


        val logado =  VerificaLogin().handle(request, response) as Boolean
        if (logado){

            "ok"
        }else{
            response.status(401)
            response.body("Usuario ou senha invalidos")
        }



    }
    // fazer login
    spark.post("/verificaEmpresa"){

        //val logado = verifica.handle(request, response) as Boolean


        val logado =  VerificaEmpLogin().handle(request, response) as Boolean
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
*/


}

