package com.Sistema_SLA.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DaoIntegrationTests {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private AlocacaoDao alocacaoDao;

    @Test
    void deveBuscarUsuarioComPerfil() {
        var usuario = usuarioDao.buscaEmail("CARLOS@UNICEPLAC.EDU.BR");

        assertTrue(usuario.isPresent());
        assertEquals("ALUNO", usuario.get().getPerfil());
    }

    @Test
    void deveBuscarTodasAsAlocacoesDoUsuarioEmOrdem() {
        var alocacoes = alocacaoDao.buscaPorUsuario(1L);

        assertEquals(2, alocacoes.size());
        assertEquals("Segunda-feira", alocacoes.get(0).getDiaSemana());
        assertEquals("Terça-feira", alocacoes.get(1).getDiaSemana());
    }
}
