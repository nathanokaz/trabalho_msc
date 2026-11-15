package br.pucpr.biblioteca.banco;

import java.math.BigDecimal;

public class NotificacaoPagamento {
    private final String idCobranca;
    private final BigDecimal valorPago;
    private final SituacaoCobranca situacao;

    public NotificacaoPagamento(String idCobranca, BigDecimal valorPago, SituacaoCobranca situacao) {
        this.idCobranca = idCobranca;
        this.valorPago = valorPago;
        this.situacao = situacao;
    }

    public String getIdCobranca() { return idCobranca; }
    public BigDecimal getValorPago() { return valorPago; }
    public SituacaoCobranca getSituacao() { return situacao; }
}