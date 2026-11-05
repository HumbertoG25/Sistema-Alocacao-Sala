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

    private final AlocacaoService alocacaoService;
    private final LoginBean loginBean;
    private List<AlocacaoDetalhe> alocacoes = Collections.emptyList();
    private List<AlocacaoDetalhe> aulasDeHoje = Collections.emptyList();
    private boolean erroCarregamento;

    public AlunoBean(AlocacaoService alocacaoService, LoginBean loginBean) {
        this.alocacaoService = alocacaoService;
        this.loginBean = loginBean;
    }

    public String carregar() {
        if (!loginBean.isAluno()) {
            return "/login.xhtml?faces-redirect=true";
        }
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
