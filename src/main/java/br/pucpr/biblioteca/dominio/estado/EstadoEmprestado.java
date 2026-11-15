package br.pucpr.biblioteca.dominio.estado;

import br.pucpr.biblioteca.dominio.Livro;

public class EstadoEmprestado extends EstadoLivro {
    @Override
    public SituacaoLivro getSituacao() {
        return SituacaoLivro.EMPRESTADO;
    }

    @Override
    public void renovar(Livro livro) {
        if (livro.getNumRenovacoes() >= 2) {
            recusar("renovar");
        }
        livro.setNumRenovacoes(livro.getNumRenovacoes() + 1);
        livro.setDataVencimento(livro.getDataVencimento().plusDays(30));
        livro.alterarEstado(new EstadoRenovado());
    }

    @Override
    public void devolver(Livro livro) {
        livro.alterarEstado(new EstadoDevolvido());
    }
}