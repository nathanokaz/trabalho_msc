package br.pucpr.biblioteca.dominio;

import br.pucpr.biblioteca.dominio.estado.SituacaoLivro;
import java.time.LocalDate;

public interface iLivro {
    Long getId();
    String getTitulo();
    SituacaoLivro getSituacao();
    int getNumRenovacoes();
    LocalDate getDataVencimento();

    void reservar();
    void cancelarReserva();
    void emprestar();
    void renovar();
    void devolver();
    void aprovarInspecao();
    void reprovarInspecao();
    void concluirReparo();
    void emitirLaudoIrrecuperavel();
}