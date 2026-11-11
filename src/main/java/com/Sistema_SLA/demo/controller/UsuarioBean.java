package com.Sistema_SLA.demo.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Sistema_SLA.demo.dao.UsuarioDao;
import com.Sistema_SLA.demo.model.Usuario;
import com.Sistema_SLA.demo.service.CadastroService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
    
    // Service responsável pelas regras de negócio e persistência de usuários
    @Autowired
    private CadastroService cadastroService;

    // DAO para consultas de listagem
    @Autowired
    private UsuarioDao usuarioDao;

    private Usuario usuario = new Usuario();

    // Lista de usuários cadastrados
    private List<Usuario> usuarios;

    // Inicializa a listagem de usuários após a construção do bean
    @PostConstruct
    public void init() {
        carregarUsuarios();
    }

    // Carrega a lista atualizada de usuários
    public void carregarUsuarios() {
        this.usuarios = usuarioDao.listar();
    }

    // Método chamado ao submeter o formulário de cadastro de usuário para salvar o usuário no banco de dados
    public String salvarUsuario() {
        // Tenta cadastrar o usuário chamando o serviço de cadastro e captura qualquer exceção lançada
        try {
            cadastroService.cadastrarUsuario(usuario);
            this.usuario = new Usuario();
            carregarUsuarios();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Usuário cadastrado com sucesso!"));
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção:", e.getMessage()));
        }
        return null;
    }

    // Exclui um usuário chamando o serviço e captura a exceção de regra de negócio caso ele tenha alocações ativas
    public void excluirUsuario(Long id) {
        try {
            cadastroService.excluirUsuario(id);
            carregarUsuarios();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Usuário excluído com sucesso!"));
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção:", e.getMessage()));
        }
    }

    // Getters e Setters para o objeto de usuário e a listagem
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Usuario> getUsuarios() { return usuarios; }
}
