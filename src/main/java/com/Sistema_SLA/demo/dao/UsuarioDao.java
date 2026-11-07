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

    public Optional<Usuario> buscaEmail(String email) {
        String sql = """
                SELECT u.id, u.nome, u.email, u.senha, u.perfil_id, p.nome AS perfil
                FROM usuarios u
                JOIN perfis p ON p.id = u.perfil_id
                WHERE LOWER(u.email) = LOWER(?)
                """;

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

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, perfil_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            usuario.getNome(), 
            usuario.getEmail(), 
            usuario.getSenha(), 
            usuario.getPerfilId()
        );
    }

    public List<Usuario> listarTodos() {
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
}
