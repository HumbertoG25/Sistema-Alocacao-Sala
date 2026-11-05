package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.model.Usuario;
import com.Sistema_SLA.demo.service.AutenticacaoService;
import com.Sistema_SLA.demo.service.BancoDadosException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component("loginBean")
@SessionScope
public class LoginBean implements Serializable {

    private final AutenticacaoService autenticacaoService;
    private String email;
    private String senha;
    private Usuario usuarioLogado;

    public LoginBean(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    public String entrar() {
        try {
            return autenticacaoService.autenticar(email, senha)
                    .map(this::iniciarSessao)
                    .orElseGet(() -> {
                        adicionarErro("Não foi possível entrar", "Confira o e-mail e a senha.");
                        return null;
                    });
        } catch (BancoDadosException e) {
            adicionarErro("Banco de dados indisponível", "Tente novamente em alguns instantes.");
            return null;
        }
    }

    private void adicionarErro(String titulo, String detalhe) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalhe));
    }

    private String iniciarSessao(Usuario usuario) {
        usuarioLogado = usuario;
        senha = null;

        if (isProfessor()) {
            return "/professor/painel-professor.xhtml?faces-redirect=true";
        }
        return "/aluno/painel-aluno.xhtml?faces-redirect=true";
    }

    public String sair() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public String abrirInicio() {
        if (isProfessor()) {
            return "/professor/painel-professor.xhtml?faces-redirect=true";
        }
        if (isAluno()) {
            return "/aluno/painel-aluno.xhtml?faces-redirect=true";
        }
        return "/login.xhtml?faces-redirect=true";
    }

    public boolean isLogado() {
        return usuarioLogado != null;
    }

    public boolean isAluno() {
        return isLogado() && "ALUNO".equalsIgnoreCase(usuarioLogado.getPerfil());
    }

    public boolean isProfessor() {
        return isLogado() && "PROFESSOR".equalsIgnoreCase(usuarioLogado.getPerfil());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
