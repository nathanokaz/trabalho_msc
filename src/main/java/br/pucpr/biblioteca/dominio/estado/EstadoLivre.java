package br.pucpr.biblioteca.dominio.estado;

import br.pucpr.biblioteca.dominio.Livro;
import java.time.LocalDate;

public class EstadoLivre extends EstadoLivro {
    @Override
    public SituacaoLivro getSituacao() {
        return SituacaoLivro.LIVRE;
    }

    @Override
    public void reservar(Livro livro) {
        livro.alterarEstado(new EstadoReservado());
    }

    @Override
    public void emprestar(Livro livro) {
        livro.setNumRenovacoes(0);
        livro.setDataVencimento(LocalDate.now().plusDays(30));
        livro.alterarEstado(new EstadoEmprestado());
    }
}