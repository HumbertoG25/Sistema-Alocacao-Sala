package com.Sistema_SLA.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Sistema_SLA.demo.model.Disciplina;

@Repository
public class DisciplinaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insere uma nova disciplina no banco de dados
    public void inserir(Disciplina disciplina) {
        String sql = "INSERT INTO disciplinas (nome) VALUES (?)";
        jdbcTemplate.update(sql, disciplina.getNome());
    }

    // Lista todas as disciplinas do banco de dados
    public List<Disciplina> listar() {
        String sql = "SELECT id, nome FROM disciplinas";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Disciplina disciplina = new Disciplina();
            disciplina.setId(rs.getLong("id"));
            disciplina.setNome(rs.getString("nome"));
            return disciplina;
        });
    }

    // Deleta uma disciplina do banco de dados com base no ID fornecido
    public void deletar(Long id) {
        String sql = "DELETE FROM disciplinas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

