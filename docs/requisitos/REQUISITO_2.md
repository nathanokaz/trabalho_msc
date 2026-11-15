# Requisito 2 — Políticas de Cálculo de Multa (Strategy)

## O que foi feito
* **Padrão GoF Strategy** aplicado para calcular multas por atraso sem condicionais (`if/switch`) no serviço de multas ou na emissão de boletos[cite: 3].
* **`PoliticaMulta` (Interface Strategy):** Define o contrato `calcular(dataVencimento, dataDevolucao)` e o método auxiliar `calcularDiasAtraso(...)` usando `ChronoUnit.DAYS.between(...)` protegido com `Math.max(0, ...)`[cite: 3].
* **3 Estratégias Concretas:**
  * `PoliticaMultaOriginal`: cobra **R$ 1,00** por dia de atraso[cite: 3].
  * `PoliticaMultaComTeto`: cobra **R$ 1,00** por dia, limitado ao teto máximo de **R$ 20,00**[cite: 3].
  * `PoliticaMultaComTolerancia`: concede **2 dias de tolerância** gratuitos e cobra **R$ 1,00** a partir do 3º dia de atraso[cite: 3].
* **`ConfiguracaoAdministrativa`:** Armazena a política vigente e permite trocá-la dinamicamente via `setPoliticaMultaVigente(...)`[cite: 3].
* **`Multa` e `ServicoMultas`:** O serviço executa a política ativa e grava o `valor` calculado (`BigDecimal`) e o nome da `politicaAplicada` como dados fixos na entidade `Multa` no momento da emissão[cite: 3].
* **Testes (`R02StrategyTest`):** Validam 5 dias de atraso gerando `R$ 5,00` (original) e `R$ 3,00` (tolerância), devolução sem atraso gerando `R$ 0,00`, limite de `R$ 20,00` no teto e imutabilidade das multas já emitidas após troca de política[cite: 3].

## Respostas da Arguição
1. **Inclusão de nova política sem alterar o serviço:** Basta criar uma nova classe implementando `PoliticaMulta` (Princípio Aberto/Fechado) e registrá-la na `ConfiguracaoAdministrativa`. Como o `ServicoMultas` depende apenas da interface, ele não sofre alteração[cite: 3].
2. **Por que a troca não recalcula multas já emitidas:** No momento da emissão, o valor final (`BigDecimal`) e o nome da política são gravados como valores históricos fixos dentro do objeto `Multa`. A troca afeta apenas emissões futuras[cite: 3].
3. **Cálculo de dias e valores monetários:** Os dias de atraso são calculados com `Math.max(0, ChronoUnit.DAYS.between(vencimento, devolucao))` (nunca gerando dias negativos), e os valores usam `BigDecimal` com escala de 2 casas decimais para evitar erros de precisão financeira[cite: 3].