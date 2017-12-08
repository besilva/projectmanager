package modelo

import java.util.*
import javax.print.attribute.IntegerSyntax

interface Modelo{
        var codigo: Int?
        fun setAtualizacao(updated: Locale)
        // deve refletir quando o registro foi inserido ou atualizado
        fun getAtualizacao()
        fun isPersistente() = this.codigo != null


}