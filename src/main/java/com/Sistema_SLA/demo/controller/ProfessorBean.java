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

@Component("professorBean")
@ViewScoped
public class ProfessorBean implements Serializable {

    // Service responsável por buscar as alocações do usuário
    private final AlocacaoService alocacaoService;
    private final LoginBean loginBean;
    private List<AlocacaoDetalhe> alocacoes = Collections.emptyList();
    private boolean erroCarregamento;

    // Construtor do bean, injetando o serviço de alocação e o bean de login
    public ProfessorBean(AlocacaoService alocacaoService, LoginBean loginBean) {
        this.alocacaoService = alocacaoService;
        this.loginBean = loginBean;
    }

    // Método chamado para carregar as alocações do usuário logado
    public String carregar() {
        // Verifica se o usuário logado é um professor; caso contrário, redireciona para a página de login
        if (!loginBean.isProfessor()) {
            return "/login.xhtml?faces-redirect=true";
        }
        // Tenta buscar as alocações do usuário logado, lidando com possíveis exceções de acesso a dados
        try {
            alocacoes = alocacaoService.buscarPorUsuario(loginBean.getUsuarioLogado().getId());
            erroCarregamento = false;
        } catch (BancoDadosException e) {
            alocacoes = Collections.emptyList();
            erroCarregamento = true;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Não foi possível carregar as alocações", "Verifique a conexão com o banco de dados."));
        }
        return null;
    }

    // Método auxiliar para listar de alocações do professor
    public List<AlocacaoDetalhe> getAlocacoes() {
        return alocacoes;
    }

    // Método auxiliar para verificar se o professor não tem alocações
    public boolean isSemAlocacoes() {
        return alocacoes.isEmpty();
    }

    // Método auxiliar para verificar se houve erro ao carregar as alocações
    public boolean isErroCarregamento() {
        return erroCarregamento;
    }
}
