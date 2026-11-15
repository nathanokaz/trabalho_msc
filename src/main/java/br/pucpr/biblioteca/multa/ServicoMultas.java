package br.pucpr.biblioteca.multa;

import br.pucpr.biblioteca.config.ConfiguracaoAdministrativa;
import br.pucpr.biblioteca.multa.Multa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServicoMultas {
    private final ConfiguracaoAdministrativa config;

    public ServicoMultas(ConfiguracaoAdministrativa config) {
        this.config = config;
    }

    public Multa calcularEEmitir(String idEstudante, LocalDate dataVencimento, LocalDate dataDevolucao) {
        PoliticaMulta politica = config.getPoliticaMultaVigente();
        BigDecimal valorCalculado = politica.calcular(dataVencimento, dataDevolucao);

        Multa multa = new Multa(
                idEstudante,
                valorCalculado,
                politica.getNome(),
                dataDevolucao
        );

        emitirBoleto(multa);
        return multa;
    }

    private void emitirBoleto(Multa multa) {
        // Fluxo único de emissão de boleto, independente da política escolhida
        // (No Requisito 3, este ponto delega para o contrato institucional de cobrança)
    }
}