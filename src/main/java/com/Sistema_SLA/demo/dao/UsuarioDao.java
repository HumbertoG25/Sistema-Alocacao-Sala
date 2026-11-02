package com.Sistema_SLA.demo.dao;

import com.Sistema_SLA.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository 
public class UsuarioDao {
    
    @Autowired 
    private JdbcTemplate jdbcTemplate;

    public Usuario BuscaEmail(String email){
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try{
            return jdbcTemplate.queryForObject(sql, new RowMapper<Usuario>() {
                @Override 
                public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setPerfilId(rs.getLong("perfil_id"));
                    return usuario;
                }
            }, email);
        } catch (Exception e){
            return null;
        }
    }

    
}