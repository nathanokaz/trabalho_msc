package br.pucpr.biblioteca;

import br.pucpr.biblioteca.dominio.Livro;
import br.pucpr.biblioteca.dominio.OperacaoRecusadaException;
import br.pucpr.biblioteca.dominio.estado.SituacaoLivro;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class R01StateTest {

    @Test
    void R01_terceiraRenovacaoRecusada() {
        Livro livro = new Livro(1L, "Padrões de Projeto");
        livro.emprestar();

        // 1ª renovação (válida)
        livro.renovar();
        assertEquals(SituacaoLivro.RENOVADO, livro.getSituacao());
        assertEquals(1, livro.getNumRenovacoes());

        // 2ª renovação (válida)
        livro.renovar();
        assertEquals(2, livro.getNumRenovacoes());
        LocalDate vencimentoAposSegundaRenovacao = livro.getDataVencimento();

        // 3ª renovação (deve ser recusada sem alterar o vencimento)
        assertThrows(OperacaoRecusadaException.class, livro::renovar);
        assertEquals(vencimentoAposSegundaRenovacao, livro.getDataVencimento());
        assertEquals(2, livro.getNumRenovacoes());
    }

    @Test
    void R01_livroDevolvidoNaoInspecionadoNaoPodeSerReservado() {
        Livro livro = new Livro(2L, "Arquitetura Limpa");
        livro.emprestar();
        livro.devolver();

        assertEquals(SituacaoLivro.DEVOLVIDO, livro.getSituacao());
        assertThrows(OperacaoRecusadaException.class, livro::reservar);
        assertEquals(SituacaoLivro.DEVOLVIDO, livro.getSituacao());
    }

    @Test
    void R01_laudoIrrecuperavelLevaReparandoParaIncinerado() {
        Livro livro = new Livro(3L, "Código Limpo");
        livro.emprestar();
        livro.devolver();
        livro.reprovarInspecao();

        assertEquals(SituacaoLivro.REPARANDO, livro.getSituacao());

        livro.emitirLaudoIrrecuperavel();
        assertEquals(SituacaoLivro.INCINERADO, livro.getSituacao());
        assertThrows(OperacaoRecusadaException.class, livro::reservar);
    }
}