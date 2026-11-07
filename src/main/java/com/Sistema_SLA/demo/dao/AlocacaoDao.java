package com.Sistema_SLA.demo.dao;

import com.Sistema_SLA.demo.model.Alocacao;
import com.Sistema_SLA.demo.model.AlocacaoDetalhe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlocacaoDao {

    private final JdbcTemplate jdbcTemplate;

    public AlocacaoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AlocacaoDetalhe> buscaPorUsuario(Long idUsuario) {
        String sql = """
                SELECT a.id, d.nome AS disciplina, a.horario, a.dia_semana,
                       s.bloco, s.nome AS sala, s.tipo AS tipo_sala
                FROM alocacoes a
                JOIN disciplinas d ON d.id = a.disciplina_id
                JOIN salas s ON s.id = a.sala_id
                WHERE a.usuario_id = ?
                ORDER BY CASE a.dia_semana
                    WHEN 'Segunda-feira' THEN 1
                    WHEN 'Terça-feira' THEN 2
                    WHEN 'Quarta-feira' THEN 3
                    WHEN 'Quinta-feira' THEN 4
                    WHEN 'Sexta-feira' THEN 5
                    WHEN 'Sábado' THEN 6
                    ELSE 7
                END, a.horario
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            AlocacaoDetalhe alocacao = new AlocacaoDetalhe();
            alocacao.setId(rs.getLong("id"));
            alocacao.setDisciplina(rs.getString("disciplina"));
            alocacao.setHorario(rs.getString("horario"));
            alocacao.setDiaSemana(rs.getString("dia_semana"));
            alocacao.setBloco(rs.getString("bloco"));
            alocacao.setSala(rs.getString("sala"));
            alocacao.setTipoSala(rs.getString("tipo_sala"));
            return alocacao;
        }, idUsuario);
    }

    public void inserir(Alocacao alocacao) {
        String sql = "INSERT INTO alocacoes (usuario_id, sala_id, disciplina_id, horario, dia_semana) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            alocacao.getUsuarioId(), 
            alocacao.getSalaId(), 
            alocacao.getDisciplinaId(), 
            alocacao.getHorario(), 
            alocacao.getDiaSemana()
        );
    }
}
