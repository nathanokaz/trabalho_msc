package br.pucpr.biblioteca.dominio.estado;

//enumerate que representa todos os estados que um livro pode estar
public enum SituacaoLivro {
    LIVRE,
    RESERVADO,
    EMPRESTADO,
    RENOVADO,
    DEVOLVIDO,
    REPARANDO,
    INCINERADO
}