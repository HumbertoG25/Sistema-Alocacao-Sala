package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.model.AlocacaoDetalhe;
import com.Sistema_SLA.demo.service.AlocacaoService;
import com.Sistema_SLA.demo.service.BancoDadosException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Component("alunoBean")
@ViewScoped
public class AlunoBean implements Serializable {

    // Service responsável por buscar as alocações do usuário
    private final AlocacaoService alocacaoService;
    private final LoginBean loginBean;
    private List<AlocacaoDetalhe> alocacoes = Collections.emptyList();
    private List<AlocacaoDetalhe> aulasDeHoje = Collections.emptyList();
    private boolean erroCarregamento;

    // Construtor do bean, injetando o serviço de alocação e o bean de login
    public AlunoBean(AlocacaoService alocacaoService, LoginBean loginBean) {
        this.alocacaoService = alocacaoService;
        this.loginBean = loginBean;
    }

    // Método chamado para carregar as alocações e aulas do usuário logado
    public String carregar() {
        // Verifica se o usuário logado é um aluno; caso contrário, redireciona para a página de login
        if (!loginBean.isAluno()) {
            return "/login.xhtml?faces-redirect=true";
        }
        // Tenta buscar as alocações e aulas do usuário logado, lidando com possíveis exceções de acesso a dados
        try {
            Long idUsuario = loginBean.getUsuarioLogado().getId();
            alocacoes = alocacaoService.buscarPorUsuario(idUsuario);
            aulasDeHoje = alocacaoService.buscarAulasDeHoje(idUsuario);
            erroCarregamento = false;
        } catch (BancoDadosException e) {
            alocacoes = Collections.emptyList();
            aulasDeHoje = Collections.emptyList();
            erroCarregamento = true;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Não foi possível carregar as aulas", "Verifique a conexão com o banco de dados."));
        }
        return null;
    }

    // Getters e Setters para o objeto de alocação e as listas de alocações e aulas de hoje,
    //  além do indicador de erro de carregamento

    public List<AlocacaoDetalhe> getAlocacoes() {
        return alocacoes;
    }

    public boolean isSemAlocacoes() {
        return alocacoes.isEmpty();
    }

    public List<AlocacaoDetalhe> getAulasDeHoje() {
        return aulasDeHoje;
    }

    public boolean isSemAulasHoje() {
        return aulasDeHoje.isEmpty();
    }

    public boolean isErroCarregamento() {
        return erroCarregamento;
    }
}
