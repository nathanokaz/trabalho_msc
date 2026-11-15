package br.pucpr.biblioteca.config;

import br.pucpr.biblioteca.banco.AdaptadorBancoA;
import br.pucpr.biblioteca.banco.BancoASimulado;
import br.pucpr.biblioteca.banco.CobrancaInstitucional;
import br.pucpr.biblioteca.multa.PoliticaMulta;
import br.pucpr.biblioteca.multa.PoliticaMultaOriginal;

public class ConfiguracaoAdministrativa {
    private PoliticaMulta politicaMultaVigente;
    private CobrancaInstitucional cobrancaVigente;

    public ConfiguracaoAdministrativa() {
        this.politicaMultaVigente = new PoliticaMultaOriginal();
        this.cobrancaVigente = new AdaptadorBancoA(new BancoASimulado());
    }

    public PoliticaMulta getPoliticaMultaVigente() {
        return politicaMultaVigente;
    }

    public void setPoliticaMultaVigente(PoliticaMulta novaPolitica) {
        if (novaPolitica == null) {
            throw new IllegalArgumentException("Política de multa não pode ser nula.");
        }
        this.politicaMultaVigente = novaPolitica;
    }

    public CobrancaInstitucional getCobrancaVigente() {
        return cobrancaVigente;
    }

    public void setCobrancaVigente(CobrancaInstitucional novaCobranca) {
        if (novaCobranca == null) {
            throw new IllegalArgumentException("Serviço de cobrança não pode ser nulo.");
        }
        this.cobrancaVigente = novaCobranca;
    }
}