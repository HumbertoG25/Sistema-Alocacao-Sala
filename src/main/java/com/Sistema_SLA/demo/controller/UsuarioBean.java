package com.Sistema_SLA.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.Sistema_SLA.demo.model.Usuario;
import com.Sistema_SLA.demo.service.CadastroService;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class UsuarioBean {
    
    // Service responsável por cadastrar usuários no sistema
    @Autowired
    private CadastroService cadastroService;

    private Usuario usuario = new Usuario();

    // Método chamado ao submeter o formulário de cadastro de usuário para salvar o usuário no banco de dados
    public String salvarUsuario() {
        // Tenta cadastrar o usuário chamando o serviço de cadastro e captura qualquer exceção lançada
        try{
            cadastroService.cadastrarUsuario(usuario);
            this.usuario = new Usuario();
        }catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", e.getMessage()));
        }
        return null;
    }

    // Getters e Setters para o objeto de usuário
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
