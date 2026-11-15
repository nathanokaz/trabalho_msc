# Requisito 1 — Ciclo de Vida do Livro (State)

## O que foi feito
* **Padrão GoF State** aplicado para controlar os **7 estados** do livro (`LIVRE`, `RESERVADO`, `EMPRESTADO`, `RENOVADO`, `DEVOLVIDO`, `REPARANDO`, `INCINERADO`) sem `if/switch` na classe `Livro` ou nos tratadores[cite: 3].
* **`Livro` (Contexto):** Nasce com `estadoAtual = new EstadoLivre()` e delega cada evento passando a si mesmo (`estadoAtual.<acao>(this)`).
* **`EstadoLivro` (Classe Abstrata):** Define todos os eventos e bloqueia tudo por padrão via `recusar(...)`, lançando `OperacaoRecusadaException`.
* **7 Classes Concretas (`EstadoLivre` a `EstadoIncinerado`):** Herdam de `EstadoLivro` e sobrescrevem (`@Override`) **apenas** as operações válidas naquele estado (incluindo `emitirLaudoIrrecuperavel()` em `EstadoReparando` $\rightarrow$ `INCINERADO`)[cite: 3].
* **Testes (`R01StateTest`):** Validam a recusa na 3ª renovação sem alterar o vencimento, o bloqueio de reserva antes da inspeção pós-devolução e o bloqueio total em `INCINERADO`[cite: 3].

## Respostas da Arguição
1. **Guarda da renovação:** Fica no início de `renovar()` em `EstadoEmprestado` e `EstadoRenovado` (`if (numRenovacoes >= 2)`). Vem *antes* de somar 30 dias para que a 3ª tentativa lance exceção sem adulterar a data de vencimento[cite: 3].
2. **Evento em estado inválido:** Como a classe concreta não sobrescreve aquele método, a chamada sobe por herança para `EstadoLivro` e cai em `recusar(...)`, lançando exceção sem alterar nada no livro[cite: 3].
3. **Persistência:** Grava-se no banco apenas o nome do `enum SituacaoLivro` (`getSituacao()`). Ao ler do banco, `livro.restaurarEstado(situacao)` instancia novamente o objeto `new Estado...()` correspondente[cite: 3].