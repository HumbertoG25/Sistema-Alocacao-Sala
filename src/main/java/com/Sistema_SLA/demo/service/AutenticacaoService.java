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

    // Autentica um usuário com base no email e senha fornecidos
    public Optional<Usuario> autenticar(String email, String senha) {

        // Verifica se o email e a senha são válidos (não nulos e não em branco)
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            return Optional.empty();
        }

        // Tenta buscar o usuário pelo email e compara a senha fornecida com a senha armazenada no banco de dados
        try {
            return usuarioDao.buscaEmail(email.trim())
                    .filter(usuario -> usuario.getSenha().equals(senha));
        } catch (DataAccessException e) {
            throw new BancoDadosException("Não foi possível consultar o usuário.", e);
        }
    }
}
