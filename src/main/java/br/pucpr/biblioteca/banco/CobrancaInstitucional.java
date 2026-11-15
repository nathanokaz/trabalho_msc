package br.pucpr.biblioteca.banco;

public interface CobrancaInstitucional {
    Boleto emitirBoleto(SolicitacaoCobranca solicitacao);
    SituacaoCobranca consultarSituacao(String idCobranca);
    NotificacaoPagamento traduzirNotificacao(Object notificacaoBrutaBanco);
}