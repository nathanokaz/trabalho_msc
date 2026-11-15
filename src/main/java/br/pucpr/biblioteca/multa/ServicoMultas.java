package br.pucpr.biblioteca.multa;

import br.pucpr.biblioteca.banco.Boleto;
import br.pucpr.biblioteca.banco.CobrancaInstitucional;
import br.pucpr.biblioteca.banco.NotificacaoPagamento;
import br.pucpr.biblioteca.banco.SituacaoCobranca;
import br.pucpr.biblioteca.banco.SolicitacaoCobranca;
import br.pucpr.biblioteca.config.ConfiguracaoAdministrativa;
import br.pucpr.biblioteca.multa.Multa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ServicoMultas {
    private final ConfiguracaoAdministrativa config;
    private final Map<String, Multa> multasPorIdCobranca = new HashMap<>();

    public ServicoMultas(ConfiguracaoAdministrativa config) {
        this.config = config;
    }

    public Multa calcularEEmitir(String idEstudante, LocalDate dataVencimento, LocalDate dataDevolucao) {
        return calcularEEmitir(idEstudante, "000.000.000-00", dataVencimento, dataDevolucao);
    }

    public Multa calcularEEmitir(String idEstudante, String cpfEstudante, LocalDate dataVencimento, LocalDate dataDevolucao) {
        PoliticaMulta politica = config.getPoliticaMultaVigente();
        BigDecimal valorCalculado = politica.calcular(dataVencimento, dataDevolucao);

        Multa multa = new Multa(
                idEstudante,
                valorCalculado,
                politica.getNome(),
                dataDevolucao
        );

        if (valorCalculado.compareTo(BigDecimal.ZERO) > 0) {
            emitirBoleto(multa, cpfEstudante);
        }
        return multa;
    }

    private void emitirBoleto(Multa multa, String cpfEstudante) {
        CobrancaInstitucional cobranca = config.getCobrancaVigente();
        SolicitacaoCobranca solicitacao = new SolicitacaoCobranca(
                multa.getId(),
                cpfEstudante,
                multa.getValor()
        );

        Boleto boleto = cobranca.emitirBoleto(solicitacao);
        multa.setBoleto(boleto);
        multasPorIdCobranca.put(boleto.getIdCobranca(), multa);
    }

    /**
     * Traduz a notificação do banco pelo adaptador vigente e quita a multa uma única vez.
     * Retorna true se quitou agora ou false se a notificação for repetida / não paga.
     */
    public boolean processarNotificacaoPagamento(Object notificacaoBrutaBanco) {
        CobrancaInstitucional cobranca = config.getCobrancaVigente();
        NotificacaoPagamento notificacao = cobranca.traduzirNotificacao(notificacaoBrutaBanco);

        if (notificacao.getSituacao() != SituacaoCobranca.PAGA) {
            return false;
        }

        Multa multa = multasPorIdCobranca.get(notificacao.getIdCobranca());
        if (multa == null) {
            throw new IllegalArgumentException("Cobrança não encontrada: " + notificacao.getIdCobranca());
        }

        return multa.quitar();
    }
}