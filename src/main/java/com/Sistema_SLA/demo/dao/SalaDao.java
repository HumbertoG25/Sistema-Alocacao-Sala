package com.Sistema_SLA.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Sistema_SLA.demo.model.Sala;

@Repository
public class SalaDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inserir(Sala sala) {
        String sql = "INSERT INTO salas (nome, tipo, bloco) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, sala.getNome(), sala.getTipo(), sala.getBloco());
    }

    public List<Sala> listar() {
        String sql = "SELECT id, nome, bloco, tipo FROM salas";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Sala sala = new Sala();
            sala.setId(rs.getLong("id"));
            sala.setNome(rs.getString("nome"));
            sala.setBloco(rs.getString("bloco"));
            sala.setTipo(rs.getString("tipo"));
            return sala;
        });
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM salas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
