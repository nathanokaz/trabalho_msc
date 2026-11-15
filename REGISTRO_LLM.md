# REGISTRO_LLM

Resumo dos principais pedidos feitos ao modelo e do que a equipe aceitou, corrigiu ou descartou.

## Ferramentas e modelos

| Ferramenta | Modelo | Uso |
|---|---|---|
| GitHub Copilot Chat (VS Code, modo agente) | Claude Sonnet 5.5 | Planejamento, código, testes e documentação (detalhado por pedido abaixo) |

## Registro de pedidos

Decisão: **Aceito** (usado como veio), **Corrigido** (aceito com ajustes da equipe) ou **Descartado**.

| # | Data | Pedido ao modelo | O que foi gerado (arquivos) | Decisão | Observações da equipe |
|---|---|---|---|---|---|
| 1 | 2026-11-14 | Leitura do enunciado e estimativa de tamanho do projeto | Nenhum código; estimativa de 3.000 a 6.500 linhas de Java | n/a | Apenas orientação |
| 2 | 2026-11-14 | Por onde começar o projeto; criação deste registro | Nenhum código; ordem sugerida de implementação e estrutura deste arquivo | n/a | Apenas orientação |
| 3 | 2026-11-14 | Criar a estrutura do projeto sem implementar os requisitos (Java 17, Maven, MySQL conforme o PDF, nada instalado) | `pom.xml`, `.gitignore`, `README.md`, `Main.java` vazio e um `package-info.java` por pacote em `src/main/java/br/pucpr/biblioteca` | Aceito | Nenhuma regra de domínio gerada; a divisão de pacotes por requisito deve ser conferida contra o diagrama de classes do Trabalho 02 |
| 4 | 2026-11-14 | Opinião sobre usar front end web com Thymeleaf | Nenhum código; recomendação de começar pela interface de linha de comando (o PDF aceita web ou CLI e a interface não é critério de avaliação) | n/a | A equipe ainda deve decidir a interface; web fica opcional para depois dos dez requisitos |
| 5 | 2026-11-14 | Se a estrutura de pastas estava completa | Nenhum código; apontou que faltam `src/test`, `src/main/resources` e `docs/uml` | n/a | Diretórios criados junto com o primeiro arquivo de cada um |
| 6 | 2026-11-14 | Anotar neste arquivo os pedidos feitos e o que o PDF exige sobre uso de LLM | Atualização deste `REGISTRO_LLM.md` (linhas 4 a 6 e nova regra) | Aceito | Documentação gerada por LLM, conforme o PDF |
| 7 | 2026-11-14 | Criar toda a estrutura de pastas e arquivos necessária para os 10 requisitos, sem escrever código | 95 classes e interfaces `.java` em `src/main/java` (só com a linha `package`), 10 arquivos de teste `R01` a `R10` em `src/test/java`, `package-info.java` do pacote `config`, `application.properties` e `schema.sql` vazios, `docs/uml/.gitkeep`; README atualizado | Aceito | Nomes de classes e pacotes são escolha do modelo; a equipe deve conferi-los contra os diagramas do Trabalho 02 |
| 8 | 2026-11-14 | Padronizar os nomes das classes por pacote (prefixo pelo papel) para facilitar a explicação na apresentação | Renomeados `UnidadeTrabalhoJdbc` para `UnidadeTrabalhoMySql`, `ConsultaAcervoDecorator` para `ConsultaAcervoDecorador` e `Composicao` para `MontagemSistema` (arquivos ainda só com `package`) | Aceito | `Composicao` lembrava o padrão Composite (R10); os demais pacotes já seguiam o padrão |
| 9 | 2026-11-14 | Conferir os requisitos técnicos, de LLM, entregáveis e demonstração do PDF contra o projeto | Nenhum código; lista de pendências (Java e Maven ausentes na máquina, repositório Git não iniciado, UML no Astah, data controlável na demonstração, teste do R8 sem MySQL ativo) | n/a | Apenas orientação |
| 10 | 2026-11-14 | Se já dá para começar a implementar os requisitos (sem gerar código) | Nenhum código; rascunho da máquina de estados de `Livro` a partir do PDF e lista de decisões a fechar antes do R1 | n/a | Apenas orientação; as classes continuam só com a linha `package` |

## Regras da equipe

- Todo código sem autoria humana direta é revisado e explicado por todos os integrantes antes de ser considerado implementado.
- Cada integrante deve saber responder sobre qualquer parte do sistema, não apenas a que desenvolveu.
- Cada novo pedido que gerar código aprovado é registrado na tabela acima, com a decisão da equipe.
- Código que nenhum integrante consegue explicar é considerado não implementado: a equipe revisa e muda a decisão para **Corrigido** ou **Descartado** quando necessário.
- A coluna "Partes do sistema" do README é atualizada a cada pedido que gerar código, pois o PDF exige informar em quais partes cada LLM foi usada.
