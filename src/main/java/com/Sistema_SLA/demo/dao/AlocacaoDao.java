package com.Sistema_SLA.demo.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository 
public class AlocacaoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> buscaAlocAluno(Long IdUsuario){
        String sql = "SELECT d.nome AS disciplina, a.horario, a.dia_semana, s.bloco, s.nome AS sala"+
                    "FROM alocacoes a "+
                    "JOIN disciplinas d ON a.disciplina_id = d.id "+
                    "JOIN salas s ON a.sala_id = s.id "+
                    "WHERE a.usuario_id = ?";
        try{
            return jdbcTemplate.queryForMap(sql, IdUsuario);
        } catch (Exception e){
            return null;
        }
    }

    public Map<String, Object> buscaAlocProf(Long IdUsuario){
        String sql = "SELECT d.nome AS disciplina, a.horario, a.dia_semana, s.bloco, s.nome AS sala, s.tipo AS tipo_sala " +
             "FROM alocacoes a " +
             "JOIN disciplinas d ON a.disciplina_id = d.id " +
             "JOIN salas s ON a.sala_id = s.id " +
             "WHERE a.usuario_id = ?";
        try{
            return jdbcTemplate.queryForMap(sql, IdUsuario);
        } catch (Exception e){
            return null;
        }
    }
}
