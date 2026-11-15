package br.pucpr.biblioteca.dominio.estado;

import br.pucpr.biblioteca.dominio.Livro;
import java.time.LocalDate;

public class EstadoReservado extends EstadoLivro {
    @Override
    public SituacaoLivro getSituacao() {
        return SituacaoLivro.RESERVADO;
    }

    @Override
    public void cancelarReserva(Livro livro) {
        livro.alterarEstado(new EstadoLivre());
    }

    @Override
    public void emprestar(Livro livro) {
        livro.setNumRenovacoes(0);
        livro.setDataVencimento(LocalDate.now().plusDays(30));
        livro.alterarEstado(new EstadoEmprestado());
    }
}