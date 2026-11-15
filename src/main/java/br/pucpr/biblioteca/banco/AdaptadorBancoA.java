package br.pucpr.biblioteca.banco;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AdaptadorBancoA implements CobrancaInstitucional {
    private final BancoASimulado bancoA;

    public AdaptadorBancoA(BancoASimulado bancoA) {
        this.bancoA = bancoA;
    }

    @Override
    public Boleto emitirBoleto(SolicitacaoCobranca solicitacao) {
        String cpfNumerico = solicitacao.getCpfDocumento().replaceAll("\\D", "");
        int valorCentavos = solicitacao.getValorReais()
                .multiply(new BigDecimal("100"))
                .setScale(0, RoundingMode.HALF_UP)
                .intValueExact();

        String codigoBancoA = bancoA.registrarCobrancaCentavos(
                solicitacao.getIdMulta(),
                cpfNumerico,
                valorCentavos
        );

        return new Boleto(codigoBancoA, solicitacao.getIdMulta(), solicitacao.getValorReais(), SituacaoCobranca.PENDENTE);
    }

    @Override
    public SituacaoCobranca consultarSituacao(String idCobranca) {
        return converterSituacao(bancoA.consultarStatus(idCobranca));
    }

    @Override
    public NotificacaoPagamento traduzirNotificacao(Object notificacaoBrutaBanco) {
        // Formato legado do Banco A: "CODIGO;CENTAVOS;STATUS" (ex: "BA-123;700;PAID")
        String[] partes = notificacaoBrutaBanco.toString().split(";");
        String idCobranca = partes[0];
        BigDecimal valorReais = new BigDecimal(partes[1])
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        SituacaoCobranca situacao = converterSituacao(partes[2]);

        return new NotificacaoPagamento(idCobranca, valorReais, situacao);
    }

    private SituacaoCobranca converterSituacao(String statusBancoA) {
        return switch (statusBancoA) {
            case "PAID" -> SituacaoCobranca.PAGA;
            case "OPEN" -> SituacaoCobranca.PENDENTE;
            default -> SituacaoCobranca.CANCELADA;
        };
    }
}