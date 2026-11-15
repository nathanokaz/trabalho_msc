package br.pucpr.biblioteca.multa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

//(R$ 1,00 por dia, limitada a R$ 20,00):
public class PoliticaMultaComTeto implements PoliticaMulta {
    private static final BigDecimal TETO = new BigDecimal("20.00");

    @Override
    public String getNome() {
        return "COM_TETO_20_REAIS";
    }

    @Override
    public BigDecimal calcular(LocalDate dataVencimento, LocalDate dataDevolucao) {
        long dias = calcularDiasAtraso(dataVencimento, dataDevolucao);
        BigDecimal valor = BigDecimal.valueOf(dias).setScale(2, RoundingMode.HALF_UP);
        return valor.min(TETO);
    }
}