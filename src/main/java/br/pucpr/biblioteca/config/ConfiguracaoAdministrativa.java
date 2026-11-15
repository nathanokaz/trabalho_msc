package br.pucpr.biblioteca.config;

import br.pucpr.biblioteca.multa.PoliticaMulta;
import br.pucpr.biblioteca.multa.PoliticaMultaOriginal;

public class ConfiguracaoAdministrativa {
    private PoliticaMulta politicaMultaVigente;

    public ConfiguracaoAdministrativa() {
        this.politicaMultaVigente = new PoliticaMultaOriginal();
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
}