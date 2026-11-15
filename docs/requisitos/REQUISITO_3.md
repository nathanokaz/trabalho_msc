# Requisito 3 — Integração com Sistemas Bancários Externos (Adapter)

## O que foi feito
- **Padrão GoF Adapter** aplicado para integrar múltiplos sistemas bancários com APIs incompatíveis sem acoplar o serviço de multas a detalhes técnicos ou condicionais (`if/switch`).
- **`CobrancaInstitucional` (Interface Target):** Define o contrato único da biblioteca com `emitirBoleto(SolicitacaoCobranca)` e `traduzirNotificacao(Object)`.
- **2 Adaptadores Concretos:**
  - `AdaptadorBancoA`: converte o valor de `BigDecimal` (reais) para `int` (centavos), remove a máscara do CPF (`replaceAll("\\D", "")`) para chamar `BancoASimulado.registrarCobrancaCentavos(...)`, e interpreta notificações no formato delimitado `"CODIGO;CENTAVOS;STATUS"`.
  - `AdaptadorBancoB`: converte o valor de `BigDecimal` para `double`, formata o CPF com pontuação (`000.000.000-00`) para chamar `BancoBSimulado.criarTitulo(...)`, e interpreta notificações baseadas em `Map<String, Object>`.
- **`ConfiguracaoAdministrativa`:** Armazena o adaptador vigente e permite alternar entre os bancos dinamicamente via `setCobrancaVigente(...)`.
- **`Multa` e `ServicoMultas`:** O serviço delega a emissão do boleto e a tradução das notificações exclusivamente pela interface `CobrancaInstitucional`, vinculando o `Boleto` à `Multa` e garantindo idempotência na quitação (`Multa.quitar()`).
- **Testes (`R03AdapterTest`):** Validam que a mesma solicitação de `R$ 7,00` gera `700` centavos sem máscara no Banco A e `7.0` com CPF formatado no Banco B, além da quitação da multa via notificação bruta de cada banco e proteção contra quitação duplicada.

## Respostas da Arguição
1. **Inclusão de um terceiro banco sem alterar o serviço:** Basta criar uma nova classe implementando `CobrancaInstitucional` (Princípio Aberto/Fechado) para encapsular a conversão da nova API e injetá-la na `ConfiguracaoAdministrativa`. O `ServicoMultas` não sofre nenhuma alteração.
2. **Onde ficam as conversões de formato de cada banco:** Totalmente isoladas dentro dos métodos `@Override` (`emitirBoleto` e `traduzirNotificacao`) de cada adaptador (`AdaptadorBancoA` e `AdaptadorBancoB`), mantendo o núcleo da biblioteca trabalhando apenas com `BigDecimal` e objetos de domínio (`SolicitacaoCobranca`, `Boleto`, `NotificacaoPagamento`).
3. **Como o sistema evita quitação duplicada (idempotência):** O `ServicoMultas.processarNotificacaoPagamento(...)` delega a baixa para `multa.quitar()`, que verifica a flag interna `quitada`. Na primeira notificação paga, o status muda para `QUITADA` e retorna `true`; em reenvios da mesma notificação, o método retorna `false` sem alterar o estado da multa.