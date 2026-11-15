package br.pucpr.biblioteca.multa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

//(Tolerância de 2 dias):
public class PoliticaMultaComTolerancia implements PoliticaMulta {
    @Override
    public String getNome() {
        return "COM_TOLERANCIA_2_DIAS";
    }

    @Override
    public BigDecimal calcular(LocalDate dataVencimento, LocalDate dataDevolucao) {
        long diasAtraso = calcularDiasAtraso(dataVencimento, dataDevolucao);
        long diasCobraveis = Math.max(0, diasAtraso - 2);
        return BigDecimal.valueOf(diasCobraveis).setScale(2, RoundingMode.HALF_UP);
    }
}