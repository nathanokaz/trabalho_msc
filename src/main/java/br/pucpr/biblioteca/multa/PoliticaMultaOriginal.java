package br.pucpr.biblioteca.multa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

//(R$ 1,00 por dia de atraso):
public class PoliticaMultaOriginal implements PoliticaMulta {
    @Override
    public String getNome() {
        return "ORIGINAL_1_REAL_DIA";
    }

    @Override
    public BigDecimal calcular(LocalDate dataVencimento, LocalDate dataDevolucao) {
        long dias = calcularDiasAtraso(dataVencimento, dataDevolucao);
        return BigDecimal.valueOf(dias).setScale(2, RoundingMode.HALF_UP);
    }
}