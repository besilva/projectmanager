package persistencia
import modelo.Modelo
interface IDAO<T: Modelo>{



        /**
         * Salva o objeto, se já for persistente
         * ele atualiza, caso contrário um novo
         * é inserido e o Codigo atribuído
         *
         * @param objeto   O objeto a ser persistido
         */
        fun persiste(objeto:T)

        /**
         * Seleciona o objeto pelo seu codigo.
         *
         * @param  codigo codigo do objeto na base de dados
         * @return        O objeto localizado ou null
         */
        fun seleciona(codigo: Int): T

        /**
         * Procura os registros segundo o objeto passado
         * como exemplo. Se um atributo for String deve
         * ser usado o operador like com os %.
         *
         * @param  exemplar Objeto com as propriedades a serem localizadas
         * @return          Uma lista com os objetos localizados ou uma lista vazia caso nenhum seja encontrado
         */
        fun seleciona(exemplar: T): List<T>

        /**
         * Traz todos os registros
         *
         * @return        Uma lista com todos objetos encontrados
         */
        fun seleciona(): List<T>

        /**
         * Traz vários registros
         *
         * @param  offset Deslocamento
         * @param  limit  Quantidade de registros a trazer
         * @return        Uma lista com os objetos localizados ou uma lista vazia caso nenhum seja encontrado
         */
        fun seleciona(offset: Int, limit: Int): List<T>

        /**
         * Remove o registro de um objeto, setando
         * seu código como nulo se for bem sucedido
         *
         * @param  objeto  O objeto a ser eliminado
         */
        fun remove(objeto:T)

}