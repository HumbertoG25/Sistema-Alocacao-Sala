CREATE TABLE perfis (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    perfil_id INT,
    CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

CREATE TABLE salas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    bloco VARCHAR(20) NOT NULL,
    tipo VARCHAR(30) NOT NULL
);

CREATE TABLE disciplinas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE alocacoes (
    id SERIAL PRIMARY KEY,
    usuario_id INT,
    disciplina_id INT,
    sala_id INT,
    horario VARCHAR(20) NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_disciplina FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id),
    CONSTRAINT fk_sala FOREIGN KEY (sala_id) REFERENCES salas(id)
);