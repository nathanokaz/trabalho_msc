# Sistema de gestão de biblioteca

Trabalho de Modelagem de Sistemas Computacionais (PUCPR, 2S-2026): sistema de cadastro, reserva, empréstimo, renovação e devolução de livros com padrões GoF.

## Requisitos

- Java 17 ou superior
- Maven 3.9 ou superior
- MySQL, apenas para o perfil de produção (os testes usam a família em memória)

## Como compilar, executar e testar

```
mvn test                    # compila e roda todos os testes
mvn compile exec:java       # executa a aplicação (br.pucpr.biblioteca.Main)
```

## Estrutura de pacotes (`br.pucpr.biblioteca`)

| Pacote | Conteúdo |
|---|---|
| `dominio` | Entidades e interfaces `iLivro`, `iPessoa`, `iEstudante`, `iAtendente` |
| `dominio.estado` | R1 State |
| `acesso` | Pontos internos `Acervo`, `Relacionamento`, `Movimentacao` |
| `multa` | R2 Strategy |
| `banco` | R3 Adapter |
| `circulacao` | R4 Facade |
| `circulacao.comando` | R6 Command |
| `devolucao` | R5 Observer |
| `cadastro` | R7 Template Method |
| `persistencia`, `persistencia.memoria`, `persistencia.relacional` | R8 Abstract Factory e suas duas famílias |
| `consulta` | R9 Decorator |
| `organizacao` | R10 Composite |
| `tratador` | Tratadores, únicos chamados pela interface |
| `cli` | Interface de linha de comando |
| `config` | Perfil de execução, configuração administrativa e montagem única dos componentes |

Outros diretórios: `src/test` (um teste por requisito, `R01` a `R10`), `src/main/resources` (configuração e esquema MySQL) e `docs/uml` (modelos do Astah e PDFs).

## Rastreabilidade

| Req. | Padrão | Classes e papéis | Teste |
|---|---|---|---|
| R1 | State | a preencher | a preencher |
| R2 | Strategy | a preencher | a preencher |
| R3 | Adapter | a preencher | a preencher |
| R4 | Facade | a preencher | a preencher |
| R5 | Observer | a preencher | a preencher |
| R6 | Command | a preencher | a preencher |
| R7 | Template Method | a preencher | a preencher |
| R8 | Abstract Factory | a preencher | a preencher |
| R9 | Decorator | a preencher | a preencher |
| R10 | Composite | a preencher | a preencher |

## Uso de LLMs

| Ferramenta | Modelo | Partes do sistema |
|---|---|---|
| GitHub Copilot Chat (VS Code) | Claude Sonnet 5.5 | Estrutura do projeto (`pom.xml`, pacotes, `Main`, README) e arquivos vazios de todas as classes e testes previstos |

Os pedidos feitos ao modelo e as decisões da equipe estão em [REGISTRO_LLM.md](docs/REGISTRO_LLM.md).
