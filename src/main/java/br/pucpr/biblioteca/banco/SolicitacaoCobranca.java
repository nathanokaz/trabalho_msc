package br.pucpr.biblioteca.banco;

import java.math.BigDecimal;

public class SolicitacaoCobranca {
    private final String idMulta;
    private final String cpfDocumento;
    private final BigDecimal valorReais;

    public SolicitacaoCobranca(String idMulta, String cpfDocumento, BigDecimal valorReais) {
        this.idMulta = idMulta;
        this.cpfDocumento = cpfDocumento;
        this.valorReais = valorReais;
    }

    public String getIdMulta() { return idMulta; }
    public String getCpfDocumento() { return cpfDocumento; }
    public BigDecimal getValorReais() { return valorReais; }
}