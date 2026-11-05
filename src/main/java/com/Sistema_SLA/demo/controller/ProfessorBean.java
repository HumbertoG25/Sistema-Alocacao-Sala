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

    private final AlocacaoService alocacaoService;
    private final LoginBean loginBean;
    private List<AlocacaoDetalhe> alocacoes = Collections.emptyList();
    private boolean erroCarregamento;

    public ProfessorBean(AlocacaoService alocacaoService, LoginBean loginBean) {
        this.alocacaoService = alocacaoService;
        this.loginBean = loginBean;
    }

    public String carregar() {
        if (!loginBean.isProfessor()) {
            return "/login.xhtml?faces-redirect=true";
        }
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

    public List<AlocacaoDetalhe> getAlocacoes() {
        return alocacoes;
    }

    public boolean isSemAlocacoes() {
        return alocacoes.isEmpty();
    }

    public boolean isErroCarregamento() {
        return erroCarregamento;
    }
}
