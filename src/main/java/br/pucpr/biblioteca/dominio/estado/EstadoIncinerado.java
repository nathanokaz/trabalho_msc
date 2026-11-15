package br.pucpr.biblioteca.dominio.estado;

public class EstadoIncinerado extends EstadoLivro {
    @Override
    public SituacaoLivro getSituacao() {
        return SituacaoLivro.INCINERADO;
    }
}