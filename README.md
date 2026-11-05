# Sistema de Localização Acadêmica (SLA)

Aplicação web acadêmica para alunos e professores consultarem a disciplina, o horário, o bloco e a sala de suas aulas.

## Tecnologias

- Java 17+
- Spring Boot 4.1
- Jakarta Faces (JSF) com JoinFaces
- Spring JDBC com `JdbcTemplate`
- MySQL
- Maven

## Funcionalidades

- Login com e-mail e senha
- Identificação do perfil do usuário
- Redirecionamento para o painel de aluno ou professor
- Controle de sessão e logout
- Consulta de todas as alocações do usuário
- Destaque automático das aulas do dia no painel do aluno
- Mensagens amigáveis quando o banco de dados estiver indisponível
- Painel responsivo para celular e computador

## Estrutura principal

```text
src/main/java/com/Sistema_SLA/demo/
├── controller/   Beans utilizados pelas páginas XHTML
├── dao/          Consultas SQL com JdbcTemplate
├── model/        Modelos e dados exibidos nas telas
└── service/      Regras de autenticação e alocação

src/main/resources/
├── META-INF/resources/   Páginas XHTML e CSS
└── application.properties
```

## Configuração do banco

1. Instale e inicie o MySQL.
2. Execute o arquivo `bkp_script-banco.sql` no MySQL Workbench ou em outro cliente.
3. Configure as credenciais do banco.

O projeto usa estas variáveis de ambiente:

| Variável | Valor padrão |
|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/sistemasla` |
| `DB_USERNAME` | `root` |
| `DB_PASSWORD` | vazio |

No PowerShell, as variáveis podem ser definidas antes de iniciar a aplicação:

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="sua_senha"
```

Também é possível alterar os valores diretamente em `src/main/resources/application.properties`, mas não é recomendado salvar uma senha real no repositório.

## Executando

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Em Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Depois, abra:

```text
http://localhost:8080/
```

## Usuários de demonstração

Os usuários são criados pelo script SQL.

| Perfil | E-mail | Senha |
|---|---|---|
| Aluno | `carlos@uniceplac.edu.br` | `123456` |
| Aluno | `mariana@uniceplac.edu.br` | `123456` |
| Professor | `roberto@uniceplac.edu.br` | `123456` |
| Professor | `ana@uniceplac.edu.br` | `123456` |

## Testes

```powershell
.\mvnw.cmd test
```

As senhas do script são simples apenas para demonstração acadêmica. Em um ambiente real, elas devem ser armazenadas com hash seguro.
