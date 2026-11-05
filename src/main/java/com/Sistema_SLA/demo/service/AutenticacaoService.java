package com.Sistema_SLA.demo.service;

import com.Sistema_SLA.demo.dao.UsuarioDao;
import com.Sistema_SLA.demo.model.Usuario;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService {

    private final UsuarioDao usuarioDao;

    public AutenticacaoService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Optional<Usuario> autenticar(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            return Optional.empty();
        }

        try {
            return usuarioDao.buscaEmail(email.trim())
                    .filter(usuario -> usuario.getSenha().equals(senha));
        } catch (DataAccessException e) {
            throw new BancoDadosException("Não foi possível consultar o usuário.", e);
        }
    }
}
