package br.pucpr.biblioteca.multa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Define o contrato para calcular a multa a partir da data de vencimento e da data de devolução:
public interface PoliticaMulta {
    String getNome();
    BigDecimal calcular(LocalDate dataVencimento, LocalDate dataDevolucao);

    default long calcularDiasAtraso(LocalDate dataVencimento, LocalDate dataDevolucao) {
        if (dataVencimento == null || dataDevolucao == null) {
            return 0;
        }
        return Math.max(0, ChronoUnit.DAYS.between(dataVencimento, dataDevolucao));
    }
}