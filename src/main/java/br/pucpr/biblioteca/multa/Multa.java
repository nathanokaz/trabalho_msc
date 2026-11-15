package br.pucpr.biblioteca.multa;

import br.pucpr.biblioteca.banco.Boleto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Multa {
    private final String id;
    private final String idEstudante;
    private final BigDecimal valor;
    private final String politicaAplicada;
    private final LocalDate dataEmissao;
    private Boleto boleto;
    private boolean quitada;
    private int quantidadeQuitacoes;

    public Multa(String idEstudante, BigDecimal valor, String politicaAplicada, LocalDate dataEmissao) {
        this.id = UUID.randomUUID().toString();
        this.idEstudante = idEstudante;
        this.valor = valor;
        this.politicaAplicada = politicaAplicada;
        this.dataEmissao = dataEmissao;
        this.quitada = false;
        this.quantidadeQuitacoes = 0;
    }

    /**
     * Proteção contra notificação duplicada:
     * efetiva a quitação apenas na primeira chamada; chamadas repetidas são ignoradas.
     */
    public boolean quitar() {
        if (this.quitada) {
            return false;
        }
        this.quitada = true;
        this.quantidadeQuitacoes++;
        return true;
    }

    public String getId() { return id; }
    public String getIdEstudante() { return idEstudante; }
    public BigDecimal getValor() { return valor; }
    public String getPoliticaAplicada() { return politicaAplicada; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public Boleto getBoleto() { return boleto; }
    public void setBoleto(Boleto boleto) { this.boleto = boleto; }
    public boolean isQuitada() { return quitada; }
    public int getQuantidadeQuitacoes() { return quantidadeQuitacoes; }
}