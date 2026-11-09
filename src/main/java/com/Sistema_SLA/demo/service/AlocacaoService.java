package com.Sistema_SLA.demo.service;

import com.Sistema_SLA.demo.dao.AlocacaoDao;
import com.Sistema_SLA.demo.model.AlocacaoDetalhe;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class AlocacaoService {

    private final AlocacaoDao alocacaoDao;

    public AlocacaoService(AlocacaoDao alocacaoDao) {
        this.alocacaoDao = alocacaoDao;
    }

    // Busca todas as alocações de um usuário específico
    public List<AlocacaoDetalhe> buscarPorUsuario(Long idUsuario) {
        // Se o ID do usuário for nulo, retorna uma lista vazia
        if (idUsuario == null) {
            return Collections.emptyList();
        }

        // Tenta buscar as alocações do usuário no banco de dados, lidando com possíveis exceções de acesso a dados
        try {
            return alocacaoDao.buscaPorUsuario(idUsuario);
        } catch (DataAccessException e) {
            throw new BancoDadosException("Não foi possível consultar as alocações.", e);
        }
    }

    // busca todas as aulas de hoje de um usuário específico
    public List<AlocacaoDetalhe> buscarAulasDeHoje(Long idUsuario) {
        return buscarPorDia(idUsuario, LocalDate.now().getDayOfWeek());
    }

    // Busca todas as alocações de um usuário específico em um dia da semana específico
    public List<AlocacaoDetalhe> buscarPorDia(Long idUsuario, DayOfWeek dia) {
        String diaSemana = nomeDoDia(dia);
        return buscarPorUsuario(idUsuario).stream()
                .filter(alocacao -> diaSemana.equalsIgnoreCase(alocacao.getDiaSemana()))
                .toList();
    }

    // Converte o enum DayOfWeek para o nome correspondente do dia da semana em português
    private String nomeDoDia(DayOfWeek dia) {
        return switch (dia) {
            case MONDAY -> "Segunda-feira";
            case TUESDAY -> "Terça-feira";
            case WEDNESDAY -> "Quarta-feira";
            case THURSDAY -> "Quinta-feira";
            case FRIDAY -> "Sexta-feira";
            case SATURDAY -> "Sábado";
            case SUNDAY -> "Domingo";
        };
    }
}
