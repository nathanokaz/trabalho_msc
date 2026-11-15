package br.pucpr.biblioteca;

import br.pucpr.biblioteca.config.ConfiguracaoAdministrativa;
import br.pucpr.biblioteca.multa.Multa;
import br.pucpr.biblioteca.multa.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class R02StrategyTest {

    @Test
    void R02_cincoDiasAtrasoPoliticaOriginalETolerancia() {
        ConfiguracaoAdministrativa config = new ConfiguracaoAdministrativa();
        ServicoMultas servico = new ServicoMultas(config);

        LocalDate vencimento = LocalDate.of(2026, 11, 10);
        LocalDate devolucao = LocalDate.of(2026, 11, 15); // 5 dias de atraso

        // 1. Política original: R$ 5,00
        Multa multaOriginal = servico.calcularEEmitir("RA001", vencimento, devolucao);
        assertEquals(new BigDecimal("5.00"), multaOriginal.getValor());

        // 2. Troca para política com tolerância de 2 dias: R$ 3,00
        config.setPoliticaMultaVigente(new PoliticaMultaComTolerancia());
        Multa multaTolerancia = servico.calcularEEmitir("RA001", vencimento, devolucao);
        assertEquals(new BigDecimal("3.00"), multaTolerancia.getValor());

        // 3. Garante que a multa emitida anteriormente NÃO foi recalculada
        assertEquals(new BigDecimal("5.00"), multaOriginal.getValor());
    }

    @Test
    void R02_semAtrasoValorZero() {
        ConfiguracaoAdministrativa config = new ConfiguracaoAdministrativa();
        ServicoMultas servico = new ServicoMultas(config);

        LocalDate vencimento = LocalDate.of(2026, 11, 10);
        Multa multaNoPrazo = servico.calcularEEmitir("RA001", vencimento, vencimento);

        assertEquals(BigDecimal.ZERO.setScale(2), multaNoPrazo.getValor());
    }

    @Test
    void R02_politicaComTetoLimitaEmVinteReais() {
        ConfiguracaoAdministrativa config = new ConfiguracaoAdministrativa();
        config.setPoliticaMultaVigente(new PoliticaMultaComTeto());
        ServicoMultas servico = new ServicoMultas(config);

        LocalDate vencimento = LocalDate.of(2026, 10, 1);
        LocalDate devolucao = LocalDate.of(2026, 11, 5); // 35 dias de atraso

        Multa multaTeto = servico.calcularEEmitir("RA001", vencimento, devolucao);
        assertEquals(new BigDecimal("20.00"), multaTeto.getValor());
    }
}