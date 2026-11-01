# Sistema de Localização Acadêmica (SLA)

Projeto final desenvolvido para a disciplina do 2º Bimestre do **UNICEPLAC - Centro Universitário do Planalto Central Apparecido dos Santos**.

## 📌 Sobre o Projeto

O **Sistema de Localização Acadêmica (SLA)** é uma aplicação web baseada no padrão **MVC (Model-View-Controller)** que simula um portal acadêmico para direcionamento de alunos e professores dentro do campus.

* **Perfil Aluno:** Exibe o painel informando o nome da disciplina do dia, horário e o bloco/sala exatos para comparecimento.
* **Perfil Professor:** Exibe o painel detalhado de alocações, diferenciando salas para aulas teóricas e laboratórios para aulas práticas.

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot** (Injeção de Dependências e motor principal)
* **JSF (JavaServer Faces) / JoinFaces** (Camada de Visão `.xhtml`)
* **JDBC (Spring JdbcTemplate)** (Persistência nativa via SQL, sem ORM/Hibernate)
* **MySQL / PostgreSQL** (Banco de Dados Relacional)

## 📁 Estrutura de Arquivos e Pacotes

```text
sistema-sla/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── sistema/
│   │   │           └── sla/
│   │   │               ├── SlaApplication.java        # Classe principal do Spring Boot
│   │   │               ├── controller/                # Managed Beans do JSF (Ponte View-Controller)
│   │   │               │   ├── LoginBean.java
│   │   │               │   ├── AlunoBean.java
│   │   │               │   └── ProfessorBean.java
│   │   │               ├── dao/                       # Camada de Persistência JDBC (Padrão DAO)
│   │   │               │   ├── UsuarioDao.java
│   │   │               │   └── AlocacaoDao.java
│   │   │               ├── model/                     # Entidades / DTOs do sistema
│   │   │               │   ├── Usuario.java
│   │   │               │   ├── Sala.java
│   │   │               │   ├── Disciplina.java
│   │   │               │   └── Alocacao.java
│   │   │               └── service/                   # Regras de negócio
│   │   │                   └── AutenticacaoService.java
│   │   └── resources/
│   │       ├── application.properties                 # Configurações do banco de dados e conexões
│   │       └── schema.sql (opcional)                  # Script SQL auxiliar de inicialização
│   └── webapp/
│       ├── login.xhtml                                # Tela de login
│       ├── aluno/
│       │   └── painel-aluno.xhtml                     # Painel visual do aluno
│       └── professor/
│           └── painel-professor.xhtml                 # Painel visual do professor
├── bkp_banco.sql                                      # Script SQL principal exigido na entrega
├── pom.xml                                            # Dependências (Spring Boot, JDBC, JoinFaces/JSF)
└── README.md                                          # Documentação do projeto para o GitHub
```

## 🚀 Como Executar o Projeto

1. **Configurar o Banco de Dados:**
   * Execute o script `bkp_banco.sql` no seu SGBD para criar as tabelas e popular os dados iniciais.
   * Atualize as credenciais no arquivo `src/main/resources/application.properties`.

2. **Rodar a Aplicação:**
   * Execute a classe principal `SlaApplication.java` ou utilize o Maven:
     ```bash
     mvn spring-boot:run
     ```
   * Acesse no navegador: `http://localhost:8080/login.xhtml`

---
*Projeto acadêmico desenvolvido em grupo.*
*Incompleto, Por enquanto*
