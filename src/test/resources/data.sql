INSERT INTO perfis (nome) VALUES ('ALUNO'), ('PROFESSOR');

INSERT INTO usuarios (nome, email, senha, perfil_id) VALUES
('Carlos Aluno', 'carlos@uniceplac.edu.br', '123456', 1),
('Prof. Roberto', 'roberto@uniceplac.edu.br', '123456', 2);

INSERT INTO salas (nome, bloco, tipo) VALUES
('Sala 101', 'Bloco A', 'Teórica'),
('Laboratório 03', 'Bloco B', 'Prática');

INSERT INTO disciplinas (nome) VALUES
('Desenvolvimento Web Java'),
('Banco de Dados Relacional');

INSERT INTO alocacoes (usuario_id, disciplina_id, sala_id, horario, dia_semana) VALUES
(1, 1, 1, '19:00 - 22:30', 'Segunda-feira'),
(1, 2, 2, '19:00 - 22:30', 'Terça-feira'),
(2, 1, 1, '19:00 - 22:30', 'Segunda-feira');
