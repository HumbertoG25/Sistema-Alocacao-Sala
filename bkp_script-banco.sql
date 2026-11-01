create database SistemaSLA;
use sistemasla;

CREATE TABLE perfis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    perfil_id INT,
    CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

CREATE TABLE salas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    bloco VARCHAR(20) NOT NULL,
    tipo VARCHAR(30) NOT NULL -- Ex: 'Teórica' ou 'Prática'
);

CREATE TABLE disciplinas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE alocacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    disciplina_id INT,
    sala_id INT,
    horario VARCHAR(20) NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_disciplina FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id),
    CONSTRAINT fk_sala FOREIGN KEY (sala_id) REFERENCES salas(id)
);

select * from perfis;

INSERT INTO perfis (nome) VALUES ('ALUNO'), ('PROFESSOR');

INSERT INTO usuarios (nome, email, senha, perfil_id) VALUES 
('Carlos Aluno', 'carlos@uniceplac.edu.br', '123456', 1),
('Mariana Aluna', 'mariana@uniceplac.edu.br', '123456', 1),
('Prof. Roberto', 'roberto@uniceplac.edu.br', '123456', 2),
('Professora Ana', 'ana@uniceplac.edu.br', '123456', 2);

INSERT INTO salas (nome, bloco, tipo) VALUES 
('Sala 101', 'Bloco A', 'Teórica'),
('Laboratório de Informática 03', 'Bloco B', 'Prática');

INSERT INTO disciplinas (nome) VALUES 
('Desenvolvimento Web Java'),
('Banco de Dados Relacional');

-- Vinculando Alocações (Exemplo de onde cada um deve estar)
INSERT INTO alocacoes (usuario_id, disciplina_id, sala_id, horario, dia_semana) VALUES 
-- Aluno 1 (Carlos) assistindo aula na Sala 101
(1, 1, 1, '19:00 - 22:30', 'Segunda-feira'),

-- Professor 3 (Roberto) dando aula teórica na Sala 101
(3, 1, 1, '19:00 - 22:30', 'Segunda-feira'),

-- Professor 4 (Ana) dando aula prática no Laboratório 03
(4, 2, 2, '19:00 - 22:30', 'Terça-feira');