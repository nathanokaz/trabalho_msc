package br.pucpr.biblioteca.dominio.estado;

import br.pucpr.biblioteca.dominio.Livro;

public class EstadoDevolvido extends EstadoLivro {
    @Override
    public SituacaoLivro getSituacao() {
        return SituacaoLivro.DEVOLVIDO;
    }

    @Override
    public void aprovarInspecao(Livro livro) {
        livro.alterarEstado(new EstadoLivre());
    }

    @Override
    public void reprovarInspecao(Livro livro) {
        livro.alterarEstado(new EstadoReparando());
    }
}