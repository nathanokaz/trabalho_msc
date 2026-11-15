package br.pucpr.biblioteca.multa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Multa {
    private final String idEstudante;
    private final BigDecimal valor;
    private final String politicaAplicada;
    private final LocalDate dataEmissao;
    private boolean quitada;

    public Multa(String idEstudante, BigDecimal valor, String politicaAplicada, LocalDate dataEmissao) {
        this.idEstudante = idEstudante;
        this.valor = valor;
        this.politicaAplicada = politicaAplicada;
        this.dataEmissao = dataEmissao;
        this.quitada = false;
    }

    public String getIdEstudante() { return idEstudante; }
    public BigDecimal getValor() { return valor; }
    public String getPoliticaAplicada() { return politicaAplicada; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public boolean isQuitada() { return quitada; }
    public void quitar() { this.quitada = true; }
}