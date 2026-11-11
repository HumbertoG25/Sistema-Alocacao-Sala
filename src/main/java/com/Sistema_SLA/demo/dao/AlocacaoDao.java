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

    // Busca todas as alocações detalhadas de um usuário específico, ordenadas por dia da semana e horário
    public List<AlocacaoDetalhe> buscaPorUsuario(Long idUsuario) {
        //comando SQL para a busca no banco de dados, utilizando JOINs para obter informações detalhadas sobre a alocação
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
        
        // Executa a consulta SQL e mapeia os resultados para objetos AlocacaoDetalhe
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

    // Insere uma nova alocação no banco de dados
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

    // deleta uma alocação do banco de dados com base no ID fornecido
    public void deletar(Long id) {
        String sql = "DELETE FROM alocacoes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Verifica se um usuário possui alguma alocação ativa no banco de dados
    public boolean existeAlocacaoPorUsuario(Long usuarioId) {
        String sql = "SELECT COUNT(*) FROM alocacoes WHERE usuario_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuarioId);
        return count != null && count > 0;
    }

    public boolean existeAlocacaoPorSala(Long salaId) {
        String sql = "SELECT COUNT(*) FROM alocacoes WHERE sala_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, salaId);
        return count != null && count > 0;
    }

    public boolean existeAlocacaoPorDisciplina(Long disciplinaId){
        String sql = "SELECT COUNT(*) FROM alocacoes WHERE disciplina_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, disciplinaId);
        return count != null && count > 0;
    }

    // Lista todas as alocações com detalhes, incluindo informações sobre o usuário, sala e disciplina
    public List<Alocacao> listar() {

        // Comando SQL para busca no banco da dados
        String sql = "SELECT a.id, a.dia_semana, a.horario, " +
                 "       u.nome AS usuario_nome, u.perfil_id, " +
                 "       s.nome AS sala_nome, s.tipo AS sala_tipo, " +
                 "       d.nome AS disciplina_nome " +
                 "FROM alocacoes a " +
                 "JOIN usuarios u ON a.usuario_id = u.id " +
                 "JOIN salas s ON a.sala_id = s.id " +
                 "JOIN disciplinas d ON a.disciplina_id = d.id";

        // Executa a consulta SQL e mapeia os resultados para objetos Alocacao
        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Alocacao alocacao = new Alocacao();
            alocacao.setId(rs.getLong("id"));
            alocacao.setDiaSemana(rs.getString("dia_semana"));
            alocacao.setHorario(rs.getString("horario"));
            
            alocacao.setUsuarioNome(rs.getString("usuario_nome"));
            
            // Determina o tipo de usuário com base no perfil_id
            Long perfilId = rs.getLong("perfil_id");
            if (perfilId == 1) {
                alocacao.setUsuarioTipo("Aluno");
            } else if (perfilId == 2) {
                alocacao.setUsuarioTipo("Professor");
            } else {
                alocacao.setUsuarioTipo("Administrador");
            }

            alocacao.setSalaNome(rs.getString("sala_nome"));
            alocacao.setSalaTipo(rs.getString("sala_tipo"));
            alocacao.setDisciplinaNome(rs.getString("disciplina_nome"));
            
            return alocacao;
        });
    }

    // Verifica se existe um conflito de horário para uma determinada sala e usuário em um dia e horário específicos
    public boolean existeConflitoHorario(Long salaId, Long usuarioId, String diaSemana, String horario) {

        // Verifica se o usuário é um professor (perfil_id = 2)
        String sqlPerfil = "SELECT perfil_id FROM usuarios WHERE id = ?";
        Long perfilId = jdbcTemplate.queryForObject(sqlPerfil, Long.class, usuarioId);

        // Se o usuário não for um professor, não há conflito de horário
        if (perfilId == null || perfilId != 2) { 
            return false;
        }

        //obs 'perfilId == null' é a blindagem para o código não quebrar caso o banco retorne um valor nulo para aquele usuário
        // enquanto 'perfilId != 2' É a regra de negócio principal

        // comando SQL para verificar se já existe uma alocação para a mesma sala ou usuário no mesmo dia e horário
        String sql = """
            SELECT COUNT(*) FROM alocacoes 
            WHERE dia_semana = ? AND horario = ? 
            AND (sala_id = ? OR usuario_id = ?)
        """;
        
        // Executa a consulta SQL para verificar a existência de alocações conflitantes
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, diaSemana, horario, salaId, usuarioId);
        return count != null && count > 0;
    }
}
