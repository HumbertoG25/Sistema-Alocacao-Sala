package com.Sistema_SLA.demo.dao;

import com.Sistema_SLA.demo.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDao {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Busca um usuário pelo email no banco de dados
    public Optional<Usuario> buscaEmail(String email) {
        // comando SQL para buscar o usuário pelo email
        String sql = """
                SELECT u.id, u.nome, u.email, u.senha, u.perfil_id, p.nome AS perfil
                FROM usuarios u
                JOIN perfis p ON p.id = u.perfil_id
                WHERE LOWER(u.email) = LOWER(?)
                """;

        // Executa a consulta SQL e mapeia o resultado para um objeto Usuario, retornando um Optional
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setPerfilId(rs.getLong("perfil_id"));
            usuario.setPerfil(rs.getString("perfil"));
            return usuario;
        }, email).stream().findFirst();
    }

    // Insere um novo usuário no banco de dados
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, perfil_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            usuario.getNome(), 
            usuario.getEmail(), 
            usuario.getSenha(), 
            usuario.getPerfilId()
        );
    }

    // Lista todos os usuários do banco de dados
    public List<Usuario> listar() {
        String sql = "SELECT id, nome, email, senha, perfil_id FROM usuarios";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setPerfilId(rs.getLong("perfil_id"));
            return usuario;
        });
    }

    // Deleta um usuário do banco de dados com base no ID fornecido
    public void deletar(Long id) {
        String sql = "DELETE FROM usuariosS WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
