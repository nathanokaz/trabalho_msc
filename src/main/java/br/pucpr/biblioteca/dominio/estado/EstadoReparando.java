package br.pucpr.biblioteca.dominio.estado;

import br.pucpr.biblioteca.dominio.Livro;

public class EstadoReparando extends EstadoLivro {
    @Override
    public SituacaoLivro getSituacao() {
        return SituacaoLivro.REPARANDO;
    }

    @Override
    public void concluirReparo(Livro livro) {
        livro.alterarEstado(new EstadoLivre());
    }

    @Override
    public void emitirLaudoIrrecuperavel(Livro livro) {
        livro.alterarEstado(new EstadoIncinerado());
    }
}