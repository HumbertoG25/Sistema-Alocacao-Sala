package com.Sistema_SLA.demo.service;

import com.Sistema_SLA.demo.dao.AlocacaoDao;
import com.Sistema_SLA.demo.model.AlocacaoDetalhe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessResourceFailureException;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AlocacaoServiceTests {

    private AlocacaoDao alocacaoDao;
    private AlocacaoService alocacaoService;

    @BeforeEach
    void preparar() {
        alocacaoDao = mock(AlocacaoDao.class);
        alocacaoService = new AlocacaoService(alocacaoDao);
    }

    @Test
    void deveFiltrarAsAulasPeloDiaDaSemana() {
        when(alocacaoDao.buscaPorUsuario(1L)).thenReturn(List.of(
                criarAlocacao("Desenvolvimento Web", "Segunda-feira"),
                criarAlocacao("Banco de Dados", "Terça-feira")
        ));

        var aulas = alocacaoService.buscarPorDia(1L, DayOfWeek.MONDAY);

        assertEquals(1, aulas.size());
        assertEquals("Desenvolvimento Web", aulas.get(0).getDisciplina());
    }

    @Test
    void deveTratarFalhaAoConsultarAlocacoes() {
        when(alocacaoDao.buscaPorUsuario(1L))
                .thenThrow(new DataAccessResourceFailureException("Banco indisponível"));

        assertThrows(BancoDadosException.class,
                () -> alocacaoService.buscarPorUsuario(1L));
    }

    private AlocacaoDetalhe criarAlocacao(String disciplina, String diaSemana) {
        AlocacaoDetalhe alocacao = new AlocacaoDetalhe();
        alocacao.setDisciplina(disciplina);
        alocacao.setDiaSemana(diaSemana);
        return alocacao;
    }
}
