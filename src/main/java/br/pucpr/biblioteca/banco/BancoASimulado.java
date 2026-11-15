package br.pucpr.biblioteca.banco;

import java.util.HashMap;
import java.util.Map;

public class BancoASimulado {
    private int ultimoValorCentavos;
    private String ultimoCpfNumerico;
    private final Map<String, String> statusPorCodigo = new HashMap<>();

    public String registrarCobrancaCentavos(String refInterna, String cpfApenasDigitos, int valorCentavos) {
        this.ultimoValorCentavos = valorCentavos;
        this.ultimoCpfNumerico = cpfApenasDigitos;
        String codigo = "BA-" + refInterna;
        statusPorCodigo.put(codigo, "OPEN");
        return codigo;
    }

    public String consultarStatus(String codigo) {
        return statusPorCodigo.getOrDefault(codigo, "CANCELED");
    }

    public void registrarPagamento(String codigo) {
        statusPorCodigo.put(codigo, "PAID");
    }

    public int getUltimoValorCentavos() { return ultimoValorCentavos; }
    public String getUltimoCpfNumerico() { return ultimoCpfNumerico; }
}