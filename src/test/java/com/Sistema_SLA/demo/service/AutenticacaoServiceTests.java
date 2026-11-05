package com.Sistema_SLA.demo.service;

import com.Sistema_SLA.demo.dao.UsuarioDao;
import com.Sistema_SLA.demo.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessResourceFailureException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AutenticacaoServiceTests {

    private UsuarioDao usuarioDao;
    private AutenticacaoService autenticacaoService;

    @BeforeEach
    void preparar() {
        usuarioDao = mock(UsuarioDao.class);
        autenticacaoService = new AutenticacaoService(usuarioDao);
    }

    @Test
    void deveAutenticarQuandoEmailESenhaEstaoCorretos() {
        Usuario usuario = new Usuario(1L, "Carlos Aluno", "carlos@uniceplac.edu.br", "123456", 1L);
        usuario.setPerfil("ALUNO");
        when(usuarioDao.buscaEmail("carlos@uniceplac.edu.br")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = autenticacaoService.autenticar("carlos@uniceplac.edu.br", "123456");

        assertTrue(resultado.isPresent());
        assertEquals("Carlos Aluno", resultado.get().getNome());
    }

    @Test
    void deveRecusarSenhaIncorreta() {
        Usuario usuario = new Usuario(1L, "Carlos Aluno", "carlos@uniceplac.edu.br", "123456", 1L);
        when(usuarioDao.buscaEmail("carlos@uniceplac.edu.br")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = autenticacaoService.autenticar("carlos@uniceplac.edu.br", "senha-errada");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRecusarCamposVazios() {
        assertTrue(autenticacaoService.autenticar("", "").isEmpty());
        assertTrue(autenticacaoService.autenticar(null, null).isEmpty());
    }

    @Test
    void deveTratarFalhaDeConexaoComBanco() {
        when(usuarioDao.buscaEmail("carlos@uniceplac.edu.br"))
                .thenThrow(new DataAccessResourceFailureException("Banco indisponível"));

        assertThrows(BancoDadosException.class,
                () -> autenticacaoService.autenticar("carlos@uniceplac.edu.br", "123456"));
    }
}
