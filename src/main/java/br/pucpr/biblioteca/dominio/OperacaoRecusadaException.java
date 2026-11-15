package br.pucpr.biblioteca.dominio;

//classe utilizada para exibir mensagens caso uma operacao seja recusada
public class OperacaoRecusadaException extends RuntimeException {
    public OperacaoRecusadaException(String mensagem) {
        super(mensagem);
    }
}