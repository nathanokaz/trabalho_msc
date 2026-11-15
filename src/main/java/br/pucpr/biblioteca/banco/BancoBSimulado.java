package br.pucpr.biblioteca.banco;

import java.util.HashMap;
import java.util.Map;

public class BancoBSimulado {
    private double ultimoValorReais;
    private String ultimoCpfFormatado;
    private final Map<Long, Integer> situacoes = new HashMap<>();
    private long contador = 1000L;

    public long gerarTitulo(String cpfFormatado, double valorEmReais) {
        this.ultimoCpfFormatado = cpfFormatado;
        this.ultimoValorReais = valorEmReais;
        long nossoNumero = ++contador;
        situacoes.put(nossoNumero, 1); // 1 = Aguardando
        return nossoNumero;
    }

    public int obterCodigoSituacao(long nossoNumero) {
        return situacoes.getOrDefault(nossoNumero, 9); // 9 = Cancelado
    }

    public void liquidarTitulo(long nossoNumero) {
        situacoes.put(nossoNumero, 2); // 2 = Liquidado
    }

    public double getUltimoValorReais() { return ultimoValorReais; }
    public String getUltimoCpfFormatado() { return ultimoCpfFormatado; }
}