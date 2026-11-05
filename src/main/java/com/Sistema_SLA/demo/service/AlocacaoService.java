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

    public List<AlocacaoDetalhe> buscarPorUsuario(Long idUsuario) {
        if (idUsuario == null) {
            return Collections.emptyList();
        }

        try {
            return alocacaoDao.buscaPorUsuario(idUsuario);
        } catch (DataAccessException e) {
            throw new BancoDadosException("Não foi possível consultar as alocações.", e);
        }
    }

    public List<AlocacaoDetalhe> buscarAulasDeHoje(Long idUsuario) {
        return buscarPorDia(idUsuario, LocalDate.now().getDayOfWeek());
    }

    public List<AlocacaoDetalhe> buscarPorDia(Long idUsuario, DayOfWeek dia) {
        String diaSemana = nomeDoDia(dia);
        return buscarPorUsuario(idUsuario).stream()
                .filter(alocacao -> diaSemana.equalsIgnoreCase(alocacao.getDiaSemana()))
                .toList();
    }

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
