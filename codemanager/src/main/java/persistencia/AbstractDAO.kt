package persistencia

import modelo.Modelo
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLType
import java.sql.Statement
import java.sql.Types
import java.time.LocalDate
import java.time.LocalDateTime

import kotlin.collections.List
import java.time.LocalTime



abstract class AbstractDAO<T: Modelo>: IDAO<T>{
    private val USUARIO = "" //your db user
    private val SENHA = "" //your db user password
    private val URL = "" //connection url
    protected fun abreConexao():Connection{
        val conexao = DriverManager.getConnection(URL, USUARIO, SENHA)
        return conexao
    }

    fun toSqlTime(localTime: LocalDateTime): java.sql.Timestamp {
        return java.sql.Timestamp.valueOf(localTime)
    }

    fun toLocalTime(time : java.sql.Timestamp): LocalDateTime {
        return time.toLocalDateTime()
    }

    override fun persiste(objeto: T) {
        val conexao =  this.abreConexao()
        val stmt : PreparedStatement
        if (objeto.isPersistente()){
            var sql = "UPDATE  " + this.getTabela() + " SET "



            val parametros = this.colunas().split(",")
            for (i in parametros.indices) {
                sql += parametros[i] + "=?,"

            }

            sql = sql.substring(0, sql.length - 1) + " where codigo =?"
            val comando = conexao.prepareStatement(sql)
            stmt = preencher(comando, objeto)

        }else{
            var sql = ("INSERT INTO " + this.getTabela() + " (codigo,"+ colunas()+ ") ")
            var sqlFim = " VALUES (Default,"
            val parametros = this.colunas().split(",")
            for (i in parametros.indices) {
                sqlFim += "?,"

            }
            sqlFim = sqlFim.substring(0, sqlFim.length - 1) + ")"
            sql += sqlFim
            val comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            stmt =  this.preencher(comando, objeto)


        }
        stmt.executeUpdate()
        val rs: ResultSet = stmt.generatedKeys

        if(rs.next()) {
            objeto.codigo = rs.getInt("codigo")
            //println("Codigo gerado: ${objeto.codigo}" )
        }
        conexao.close()
    }

    override fun remove(objeto: T) {
        val conexao = abreConexao()
        val sql = "Delete from " + getTabela() + " where codigo=?"
        val comando = conexao.prepareStatement(sql)
        if (objeto.isPersistente()) {
            comando.setInt(1, objeto.codigo!!)
            comando.execute()
        }
        conexao.close()
    }

    override fun seleciona(): List<T> {
        val  conexao = this.abreConexao()
        var sql = "Select * from " + getTabela()
        val comando = conexao.prepareStatement(sql)
        val rs = comando.executeQuery()
        conexao.close()
        return this.montaModelo(rs).toList()
    }

    override fun seleciona(codigo: Int): T {
        val  conexao = this.abreConexao()
        var sql = "Select * from " + getTabela() + " where codigo=?"
        val comando = conexao.prepareStatement(sql)
        comando.setInt(1, codigo)
        val rs = comando.executeQuery()
        conexao.close()
        return this.montaModelo(rs).get(0)
    }

    override fun seleciona(exemplar: T):List<T> {
        val  conexao = this.abreConexao()
        var sql = "Select * from " + getTabela() +" where 1=1"
        sql += geraFiltro(exemplar)
        val comando = conexao.prepareStatement(sql)
        var stmt =  this.preencherFiltro(comando, exemplar)
        val rs = stmt.executeQuery()
        conexao.close()
        return montaModelo(rs)
    }

    override fun seleciona(offset: Int, limit: Int): List<T> {
        val  conexao = this.abreConexao()
        var sql = "Select * from " + getTabela() +" LIMIT ? OFFSET ?"
        val comando = conexao.prepareStatement(sql)
        comando.setInt(1,limit)
        comando.setInt(2, offset)
        val rs = comando.executeQuery()
        conexao.close()
        return montaModelo(rs)
    }
    abstract fun juncao(): String
    abstract fun getTabela():String
    abstract fun colunas():String
    abstract fun preencher(stmt: PreparedStatement, objeto: T) : PreparedStatement
    abstract fun preencherFiltro(stmt: PreparedStatement, objeto: T) : PreparedStatement
    abstract fun montaModelo(rs: ResultSet): MutableList<T>
    abstract fun geraFiltro(exemplar: T): String
}
