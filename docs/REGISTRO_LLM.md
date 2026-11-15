# REGISTRO_LLM

Resumo dos principais pedidos feitos aos modelos e do que a equipe aceitou, corrigiu ou descartou.

## Ferramentas e modelos

| Ferramenta | Modelo | Uso |
|---|---|---|
| GitHub Copilot Chat (VS Code, modo agente) | Claude Sonnet 5.5 | Planejamento, estrutura do projeto, código, testes e documentação |
| Google Gemini (Web) | Gemini | Apoio na estruturação e geração de código de apoio dos Requisitos 1 (State), 2 (Strategy) e 3 (Adapter), roteiros de arguição e atualização de documentação |

## Registro de pedidos

Decisão: **Aceito** (usado como veio), **Corrigido** (aceito com ajustes da equipe) ou **Descartado**.

| # | Data | Pedido ao modelo | O que foi gerado (arquivos) | Decisão | Observações da equipe |
|---|---|---|---|---|---|
| 1 | 2026-11-14 | Criar a estrutura inicial do projeto Maven sem implementar os requisitos | `../pom.xml`, `.gitignore`, `README.md`, `Main.java` vazio e `package-info.java` por pacote | Aceito | Nenhuma regra de domínio gerada; divisão de pacotes a conferir com o diagrama de classes |
| 2 | 2026-11-14 | Criar toda a estrutura de arquivos necessária para os 10 requisitos, sem implementar código | 95 classes/interfaces `.java` (apenas com `package`), 10 classes de teste (`R01` a `R10`), `application.properties`, `schema.sql` e atualização do `../README.md` | Aceito | Nomes de classes e pacotes sugeridos pelo modelo; serão validados contra os diagramas |
| 3 | 2026-11-14 | Padronizar nomes de classes por pacote para facilitar a apresentação | Renomeação de `UnidadeTrabalhoJdbc` para `UnidadeTrabalhoMySql`, `ConsultaAcervoDecorator` para `ConsultaAcervoDecorador` e `Composicao` para `MontagemSistema` | Aceito | Ajuste de nomenclatura para evitar ambiguidade com padrões de projeto (ex: Composite) |
| 4 | 2026-11-14 | Implementar as classes, enums e testes do Requisito 1 (State) | `SituacaoLivro.java`, `Livro.java`, `EstadoLivro.java`, 7 classes concretas de estado e `R01StateTest.java` | Aceito | Código revisado e integrado ao projeto pela equipe |
| 5 | 2026-11-15 | Sugerir estrutura e exemplos de implementação para o Requisito 2 (Strategy — políticas de multa) | Esqueleto da interface `PoliticaMulta`, implementações de cálculo, ajustes em `ServicoMultas` e casos de teste em `R02StrategyTest.java` | Corrigido | A equipe adaptou o código aos pacotes do projeto, ajustou a imutabilidade da entidade `Multa` e validou os testes unitários |
| 6 | 2026-11-15 | Revisar perguntas de arguição e atualizar o registro de uso de LLMs | Resumo técnico do Requisito 2 e atualização do `REGISTRO_LLM.md` | Aceito | Material utilizado pela equipe para conferência com os diagramas e preparação da defesa oral |
| 7 | 2026-11-15 | Apoiar na geração dos adaptadores bancários, esclarecer dúvidas sobre o padrão Adapter e gerar documentação do Requisito 3 | Sugestões de implementação para `AdaptadorBancoA.java`, `AdaptadorBancoB.java`, integração com `ServicoMultas.java`, resumo técnico (`requisito_03_adapter.md`) e atualização do `REGISTRO_LLM.md` | Corrigido | A equipe revisou e adaptou os trechos gerados para os adaptadores e o `ServicoMultas`, integrou aos testes em `R03AdapterTest.java` e validou o fluxo para a arguição |

## Regras da equipe

- Todo código sem autoria humana direta é revisado e explicado por todos os integrantes antes de ser considerado implementado.
- Código que nenhum integrante consegue explicar é considerado não implementado, devendo ser **Corrigido** ou **Descartado**.
- Não há divisão de responsabilidade na arguição: cada integrante deve saber explicar qualquer parte do código e das funcionalidades.
- A coluna "Partes do sistema" do `../README.md` deve ser atualizada sempre que uma LLM gerar código, testes ou documentação para o projeto.