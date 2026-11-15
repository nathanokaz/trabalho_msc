package br.pucpr.biblioteca;

import br.pucpr.biblioteca.banco.AdaptadorBancoA;
import br.pucpr.biblioteca.banco.AdaptadorBancoB;
import br.pucpr.biblioteca.banco.BancoASimulado;
import br.pucpr.biblioteca.banco.BancoBSimulado;
import br.pucpr.biblioteca.config.ConfiguracaoAdministrativa;
import br.pucpr.biblioteca.multa.Multa;
import br.pucpr.biblioteca.multa.ServicoMultas;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class R03AdapterTest {

    @Test
    void R03_mesmaSolicitacaoSeteReaisConverteParaBancoAEBancoB() {
        ConfiguracaoAdministrativa config = new ConfiguracaoAdministrativa();
        ServicoMultas servico = new ServicoMultas(config);

        LocalDate vencimento = LocalDate.of(2026, 11, 1);
        LocalDate devolucao = LocalDate.of(2026, 11, 8); // 7 dias de atraso = R$ 7,00

        // 1. Banco A -> deve receber 700 centavos e CPF apenas com números
        BancoASimulado bancoA = new BancoASimulado();
        config.setCobrancaVigente(new AdaptadorBancoA(bancoA));

        Multa multaA = servico.calcularEEmitir("RA001", "123.456.789-00", vencimento, devolucao);
        assertNotNull(multaA.getBoleto());
        assertEquals(700, bancoA.getUltimoValorCentavos());
        assertEquals("12345678900", bancoA.getUltimoCpfNumerico());

        // 2. Banco B -> deve receber 7.00 (double) e CPF formatado
        BancoBSimulado bancoB = new BancoBSimulado();
        config.setCobrancaVigente(new AdaptadorBancoB(bancoB));

        Multa multaB = servico.calcularEEmitir("RA002", "12345678900", vencimento, devolucao);
        assertNotNull(multaB.getBoleto());
        assertEquals(7.00, bancoB.getUltimoValorReais(), 0.001);
        assertEquals("123.456.789-00", bancoB.getUltimoCpfFormatado());
    }

    @Test
    void R03_notificacaoRepetidaNaoGeraSegundaQuitacao() {
        ConfiguracaoAdministrativa config = new ConfiguracaoAdministrativa();
        BancoASimulado bancoA = new BancoASimulado();
        config.setCobrancaVigente(new AdaptadorBancoA(bancoA));
        ServicoMultas servico = new ServicoMultas(config);

        LocalDate vencimento = LocalDate.of(2026, 11, 1);
        LocalDate devolucao = LocalDate.of(2026, 11, 8); // R$ 7,00

        Multa multa = servico.calcularEEmitir("RA001", "123.456.789-00", vencimento, devolucao);
        String idCobranca = multa.getBoleto().getIdCobranca();
        String payloadNotificacao = idCobranca + ";700;PAID";

        // 1ª notificação: efetiva a quitação
        assertTrue(servico.processarNotificacaoPagamento(payloadNotificacao));
        assertTrue(multa.isQuitada());
        assertEquals(1, multa.getQuantidadeQuitacoes());

        // 2ª notificação idêntica (repetida): ignorada sem segunda quitação
        assertFalse(servico.processarNotificacaoPagamento(payloadNotificacao));
        assertEquals(1, multa.getQuantidadeQuitacoes());
    }
}