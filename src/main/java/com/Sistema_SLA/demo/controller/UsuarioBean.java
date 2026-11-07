package com.Sistema_SLA.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.Sistema_SLA.demo.model.Usuario;
import com.Sistema_SLA.demo.service.CadastroService;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class UsuarioBean {
    
    @Autowired
    private CadastroService cadastroService;

    private Usuario usuario = new Usuario();

    public String salvarUsuario() {
        cadastroService.cadastrarUsuario(usuario);
        this.usuario = new Usuario();
        return null;
    }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
