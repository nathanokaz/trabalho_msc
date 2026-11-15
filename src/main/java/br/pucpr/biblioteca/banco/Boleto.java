package br.pucpr.biblioteca.banco;

import java.math.BigDecimal;

public class Boleto {
    private final String idCobranca;
    private final String idMulta;
    private final BigDecimal valorReais;
    private final SituacaoCobranca situacao;

    public Boleto(String idCobranca, String idMulta, BigDecimal valorReais, SituacaoCobranca situacao) {
        this.idCobranca = idCobranca;
        this.idMulta = idMulta;
        this.valorReais = valorReais;
        this.situacao = situacao;
    }

    public String getIdCobranca() { return idCobranca; }
    public String getIdMulta() { return idMulta; }
    public BigDecimal getValorReais() { return valorReais; }
    public SituacaoCobranca getSituacao() { return situacao; }
}