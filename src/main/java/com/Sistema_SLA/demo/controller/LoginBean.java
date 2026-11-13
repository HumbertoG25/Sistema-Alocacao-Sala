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

    // Service responsável por autenticar usuários no sistema
    private final AutenticacaoService autenticacaoService;
    private String email;
    private String senha;
    private Usuario usuarioLogado;

    // Construtor do bean, injetando o serviço de autenticação
    public LoginBean(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    // Método chamado ao submeter o formulário de login para autenticar o usuário
    public String entrar() {
        // Tenta autenticar o usuário chamando o serviço de autenticação e captura qualquer exceção lançada
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

    // Método auxiliar para adicionar mensagens de erro ao contexto do JSF
    private void adicionarErro(String titulo, String detalhe) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalhe));
    }

    // Método auxiliar para iniciar a sessão do usuário logado e redirecionar para a página apropriada com base no perfil do usuário
    private String iniciarSessao(Usuario usuario) {
        usuarioLogado = usuario;
        senha = null;

        if (isAdm()) {
            return "/adm/alocacao.xhtml?faces-redirect=true";
        }
        if (isProfessor()) {
            return "/professor/painel-professor.xhtml?faces-redirect=true";
        }
        return "/aluno/painel-aluno.xhtml?faces-redirect=true";
    }

    // metodo para sair da sessao do usuario
    public String sair() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    // Método chamado para abrir a página inicial com base no perfil do usuário logado
    public String abrirInicio() {
        if (isAdm()) {
            return "/menu.xhtml?faces-redirect=true";
        }
        if (isProfessor()) {
            return "/professor/painel-professor.xhtml?faces-redirect=true";
        }
        if (isAluno()) {
            return "/aluno/painel-aluno.xhtml?faces-redirect=true";
        }
        return "/login.xhtml?faces-redirect=true";
    }

    // Métodos auxiliares para verificar o perfil do usuário logado
    public boolean isLogado() {
        return usuarioLogado != null;
    }

    public boolean isAluno() {
        return isLogado() && "ALUNO".equalsIgnoreCase(usuarioLogado.getPerfil());
    }

    public boolean isProfessor() {
        return isLogado() && "PROFESSOR".equalsIgnoreCase(usuarioLogado.getPerfil());
    }

    public boolean isAdm() {
        return isLogado() && "ADM".equalsIgnoreCase(usuarioLogado.getPerfil());
    }

    // Getters e Setters para os campos de email, senha e usuário logado
    
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
