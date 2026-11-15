package br.pucpr.biblioteca.banco;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AdaptadorBancoB implements CobrancaInstitucional {
    private final BancoBSimulado bancoB;

    public AdaptadorBancoB(BancoBSimulado bancoB) {
        this.bancoB = bancoB;
    }

    @Override
    public Boleto emitirBoleto(SolicitacaoCobranca solicitacao) {
        String digitos = solicitacao.getCpfDocumento().replaceAll("\\D", "");
        String cpfFormatado = digitos.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        double valorDouble = solicitacao.getValorReais().setScale(2, RoundingMode.HALF_UP).doubleValue();

        long nossoNumero = bancoB.gerarTitulo(cpfFormatado, valorDouble);

        return new Boleto(String.valueOf(nossoNumero), solicitacao.getIdMulta(), solicitacao.getValorReais(), SituacaoCobranca.PENDENTE);
    }

    @Override
    public SituacaoCobranca consultarSituacao(String idCobranca) {
        long nossoNumero = Long.parseLong(idCobranca);
        return converterSituacao(bancoB.obterCodigoSituacao(nossoNumero));
    }

    @Override
    public NotificacaoPagamento traduzirNotificacao(Object notificacaoBrutaBanco) {
        // Formato do Banco B: "NOSSO_NUMERO|VALOR_REAIS|CODIGO" (ex: "1001|7.00|2")
        String[] partes = notificacaoBrutaBanco.toString().split("\\|");
        String idCobranca = partes[0];
        BigDecimal valorReais = new BigDecimal(partes[1]).setScale(2, RoundingMode.HALF_UP);
        SituacaoCobranca situacao = converterSituacao(Integer.parseInt(partes[2]));

        return new NotificacaoPagamento(idCobranca, valorReais, situacao);
    }

    private SituacaoCobranca converterSituacao(int codigoBancoB) {
        return switch (codigoBancoB) {
            case 2 -> SituacaoCobranca.PAGA;
            case 1 -> SituacaoCobranca.PENDENTE;
            default -> SituacaoCobranca.CANCELADA;
        };
    }
}