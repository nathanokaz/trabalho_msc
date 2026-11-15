package br.pucpr.biblioteca.dominio;

import br.pucpr.biblioteca.dominio.estado.*;
import java.time.LocalDate;

public class Livro implements iLivro {
    private Long id;
    private String titulo;
    private EstadoLivro estadoAtual;
    private int numRenovacoes;
    private LocalDate dataVencimento;

    public Livro(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.estadoAtual = new EstadoLivre();
        this.numRenovacoes = 0;
        this.dataVencimento = null;
    }

    @Override
    public void reservar() {
        estadoAtual.reservar(this);
    }

    @Override
    public void cancelarReserva() {
        estadoAtual.cancelarReserva(this);
    }

    @Override
    public void emprestar() {
        estadoAtual.emprestar(this);
    }

    @Override
    public void renovar() {
        estadoAtual.renovar(this);
    }

    @Override
    public void devolver() {
        estadoAtual.devolver(this);
    }

    @Override
    public void aprovarInspecao() {
        estadoAtual.aprovarInspecao(this);
    }

    @Override
    public void reprovarInspecao() {
        estadoAtual.reprovarInspecao(this);
    }

    @Override
    public void concluirReparo() {
        estadoAtual.concluirReparo(this);
    }

    @Override
    public void emitirLaudoIrrecuperavel() {
        estadoAtual.emitirLaudoIrrecuperavel(this);
    }

    public void alterarEstado(EstadoLivro novoEstado) {
        this.estadoAtual = novoEstado;
    }

    public void restaurarEstado(SituacaoLivro situacao) {
        this.estadoAtual = switch (situacao) {
            case LIVRE -> new EstadoLivre();
            case RESERVADO -> new EstadoReservado();
            case EMPRESTADO -> new EstadoEmprestado();
            case RENOVADO -> new EstadoRenovado();
            case DEVOLVIDO -> new EstadoDevolvido();
            case REPARANDO -> new EstadoReparando();
            case INCINERADO -> new EstadoIncinerado();
        };
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public SituacaoLivro getSituacao() {
        return estadoAtual.getSituacao();
    }

    @Override
    public int getNumRenovacoes() {
        return numRenovacoes;
    }

    public void setNumRenovacoes(int numRenovacoes) {
        this.numRenovacoes = numRenovacoes;
    }

    @Override
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}