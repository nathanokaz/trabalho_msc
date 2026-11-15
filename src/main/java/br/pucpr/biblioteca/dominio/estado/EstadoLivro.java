package br.pucpr.biblioteca.dominio.estado;

import br.pucpr.biblioteca.dominio.Livro;
import br.pucpr.biblioteca.dominio.OperacaoRecusadaException;

public abstract class EstadoLivro {

    // 1. Obriga cada estado concreto a dizer qual item do enum ele representa
    public abstract SituacaoLivro getSituacao();

    // 2. Método auxiliar interno para padronizar a mensagem de recusa
    protected void recusar(String operacao) {
        throw new OperacaoRecusadaException(
                "Não é possível executar '" + operacao + "' quando o livro está " + getSituacao()
        );
    }

    // 3. Todos os eventos possíveis da máquina de estados.
    // Por padrão, TODOS recusam a operação. As subclasses sobrescrevem só o que aceitam.
    public void reservar(Livro livro) {
        recusar("reservar");
    }

    public void cancelarReserva(Livro livro) {
        recusar("cancelarReserva");
    }

    public void emprestar(Livro livro) {
        recusar("emprestar");
    }

    public void renovar(Livro livro) {
        recusar("renovar");
    }

    public void devolver(Livro livro) {
        recusar("devolver");
    }

    public void aprovarInspecao(Livro livro) {
        recusar("aprovarInspecao");
    }

    public void reprovarInspecao(Livro livro) {
        recusar("reprovarInspecao");
    }

    public void concluirReparo(Livro livro) {
        recusar("concluirReparo");
    }

    public void emitirLaudoIrrecuperavel(Livro livro) {
        recusar("emitirLaudoIrrecuperavel");
    }
}