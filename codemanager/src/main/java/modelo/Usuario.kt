package modelo

import org.omg.CORBA.SetOverrideType
import java.util.*
import javax.jws.WebParam
class Usuario(var nome:String?, var senha:String?, var  email:String?,override var codigo: Int?): Modelo{

    constructor(): this(null, null, null, null)


    override fun getAtualizacao() {

    }

    override fun setAtualizacao(updated: Locale) {

    }

    override fun isPersistente(): Boolean {
        return super.isPersistente()
    }
}
/*class Usuario(override var codigo: Int? = null): Modelo {


    var nome: String? = null
    var senha: String? = null
    var email: String? = null

    constructor(nome:String?, senha:String?, email:String?, codigo: Int?): this(codigo){
        var nome = nome
        var senha = senha
        var email = email
    }
    */