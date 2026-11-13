# Sistema SLA - Sistema de Localização Acadêmica

Sistema web para gestão e consulta de localização acadêmica (salas, disciplinas, horários e alocações) desenvolvido para facilitar a rotina de alunos, professores e administradores universitários.

---

## 📌 Visão Geral do Projeto

O **SLA (Sistema de Localização Acadêmica)** centraliza a distribuição física e temporal das atividades acadêmicas. O sistema disponibiliza:
- **Painel Administrativo:** Gestão completa de cadastros (usuários, salas, disciplinas) e alocação de turmas/espaços com barra lateral retrátil integrada.
- **Painel do Professor:** Consulta rápida das salas, turmas e horários de aulas designadas.
- **Painel do Aluno:** Visualização simplificada da grade horária semanal e localização das disciplinas no campus.
- **Autenticação por Perfil:** Controle de acesso baseado no tipo de usuário (`ADM`, `PROFESSOR`, `ALUNO`).

---

## 🚀 Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework Principal:** [Spring Boot 4.x](https://spring.io/projects/spring-boot)
- **Integração JSF:** [JoinFaces 6.1.2](https://joinfaces.org/)
- **Frontend / Camada de Visão:** Jakarta Faces (JSF 4 / Facelets) + CSS3 customizado
- **Persistência / Acesso a Dados:** Spring Boot Starter JDBC + MySQL Connector/J
- **Banco de Dados:** MySQL (produção/desenvolvimento) e H2 Database (testes)
- **Gerenciador de Dependências e Build:** Apache Maven (com Maven Wrapper `mvnw`)

---

## 📂 Estrutura de Pastas

```text
demo/
├── pom.xml                                      # Configurações do Maven e dependências
├── mvnw / mvnw.cmd                              # Maven Wrapper (Linux / Windows)
└── src/
    ├── main/
    │   ├── java/com/Sistema_SLA/demo/
    │   │   ├── SlaApplication.java              # Classe principal (Spring Boot Entry Point)
    │   │   ├── controller/                      # Managed Beans do JSF (@Named, @ViewScoped, @SessionScoped)
    │   │   │   ├── AlocacaoBean.java            # Controle de alocações acadêmicas
    │   │   │   ├── AlunoBean.java               # Consultas para painel do aluno
    │   │   │   ├── DisciplinaBean.java          # CRUD e listagem de disciplinas
    │   │   │   ├── LoginBean.java               # Autenticação e redirecionamento por perfil
    │   │   │   ├── ProfessorBean.java           # Consultas para painel do professor
    │   │   │   ├── SalaBean.java                # CRUD e listagem de salas
    │   │   │   ├── SidebarBean.java             # Controle de estado retrátil da sidebar
    │   │   │   └── UsuarioBean.java             # CRUD e listagem de usuários
    │   │   ├── dao/                             # Camada de persistência direta via JDBC
    │   │   │   ├── AlocacaoDao.java
    │   │   │   ├── DisciplinaDao.java
    │   │   │   ├── SalaDao.java
    │   │   │   └── UsuarioDao.java
    │   │   ├── model/                           # Entidades de domínio e DTOs
    │   │   │   ├── Alocacao.java
    │   │   │   ├── AlocacaoDetalhe.java
    │   │   │   ├── Disciplina.java
    │   │   │   ├── Sala.java
    │   │   │   └── Usuario.java
    │   │   └── service/                         # Regras de negócio e validações
    │   │       ├── AlocacaoService.java
    │   │       ├── AutenticacaoService.java
    │   │       ├── BancoDadosException.java
    │   │       └── CadastroService.java
    │   └── resources/
    │       ├── application.properties           # Configuração de conexão e runtime do Spring/JSF
    │       └── META-INF/resources/              # Páginas XHTML (Views JSF) e assets
    │           ├── index.xhtml                  # Ponto de entrada (redireciona para o painel correspondente)
    │           ├── login.xhtml                  # Tela de autenticação
    │           ├── sidebar.xhtml                # Componente reutilizável da barra lateral administrativa
    │           ├── adm/                         # Módulo do Administrador
    │           │   ├── alocacao.xhtml           # Cadastro e listagem de alocações
    │           │   ├── cadastrar-disciplina.xhtml
    │           │   ├── cadastrar-sala.xhtml
    │           │   └── cadastrar-usuario.xhtml
    │           ├── aluno/                       # Módulo do Aluno
    │           │   └── painel-aluno.xhtml       # Consulta de horários e salas
    │           ├── professor/                   # Módulo do Professor
    │           │   └── painel-professor.xhtml   # Consulta de turmas e salas
    │           └── resources/                   # Assets estáticos
    │               ├── css/estilo.css           # Estilos e temas visuais
    │               └── imgs/                    # Identidade visual e imagens
    └── test/                                    # Testes unitários e de integração
```

---

## 📋 Pré-requisitos

Antes de iniciar, certifique-se de possuir instalado em sua máquina:

- **Java JDK 17** ou superior instalado e configurado no `PATH` (`JAVA_HOME`).
- **MySQL Server 8.x** instalado e em execução (ou container Docker equivalente).
- **Git** para clonar o repositório.

---

## ⚙️ Configuração do Ambiente

1. **Crie a base de dados no MySQL:**
   ```sql
   CREATE DATABASE sistemasla CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. **Variáveis de Ambiente / Conexão:**
   As credenciais podem ser configuradas via variáveis de ambiente ou diretamente no arquivo `src/main/resources/application.properties`:

   | Propriedade / Variável | Padrão | Descrição |
   |---|---|---|
   | `DB_URL` | `jdbc:mysql://localhost:3306/sistemasla` | URL de conexão com o banco |
   | `DB_USERNAME` | `root` | Usuário do banco de dados |
   | `DB_PASSWORD` | *(vazio)* | Senha do usuário do banco |

---

## 🛠️ Como Instalar e Rodar o Sistema

### 1. Clonar o repositório
```bash
git clone <URL_DO_REPOSITORIO>
cd demo
```

### 2. Compilar o projeto
Utilize o Maven Wrapper incluso no projeto:

- **Windows:**
  ```cmd
  .\mvnw.cmd compile
  ```
- **Linux / macOS:**
  ```bash
  ./mvnw compile
  ```

### 3. Executar a aplicação
- **Windows:**
  ```cmd
  .\mvnw.cmd spring-boot:run
  ```
- **Linux / macOS:**
  ```bash
  ./mvnw spring-boot:run
  ```

---

## 🌐 Acesso ao Sistema

Após a inicialização bem-sucedida, acesse pelo navegador:

```text
http://localhost:8080/
```
ou diretamente na tela de login:
```text
http://localhost:8080/login.xhtml
```

### Perfis de Acesso e Redirecionamentos:
- **Administrador (`ADM`):** Redirecionado para `/adm/alocacao.xhtml` com acesso ao menu lateral para gestão de usuários, salas, disciplinas e alocações.
- **Professor (`PROFESSOR`):** Redirecionado para `/professor/painel-professor.xhtml`.
- **Aluno (`ALUNO`):** Redirecionado para `/aluno/painel-aluno.xhtml`.
