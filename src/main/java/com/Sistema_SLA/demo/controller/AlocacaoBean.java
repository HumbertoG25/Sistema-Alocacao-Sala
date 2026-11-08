package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.dao.AlocacaoDao;
import com.Sistema_SLA.demo.dao.DisciplinaDao;
import com.Sistema_SLA.demo.dao.SalaDao;
import com.Sistema_SLA.demo.dao.UsuarioDao;
import com.Sistema_SLA.demo.model.Alocacao;
import com.Sistema_SLA.demo.model.Disciplina;
import com.Sistema_SLA.demo.model.Sala;
import com.Sistema_SLA.demo.model.Usuario;
import com.Sistema_SLA.demo.service.CadastroService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AlocacaoBean implements Serializable{

    @Autowired
    private CadastroService cadastroService;
    
    @Autowired
    private SalaDao salaDao;
    
    @Autowired
    private DisciplinaDao disciplinaDao;
    
    @Autowired
    private UsuarioDao usuarioDao;

    // Objeto de alocação que será preenchido com os dados do formulário
    private Alocacao alocacao = new Alocacao();
    
    // Listas de salas, disciplinas e usuários que serão exibidas nos campos de seleção do formulário
    private List<Sala> listaSalas;
    private List<Disciplina> listaDisciplinas;
    private List<Usuario> listaUsuarios;

    // Método chamado após a construção do bean para inicializar as listas de salas, disciplinas e usuários
    @PostConstruct
    public void init() {
        listaSalas = salaDao.listar();
        listaDisciplinas = disciplinaDao.listar();
        listaUsuarios = usuarioDao.listar();
    }

    // Método chamado ao submeter o formulário de alocação para salvar a alocação no banco de dados
    public String salvarAlocacao() {
        // Tenta realizar a alocação chamando o serviço de cadastro e captura qualquer exceção lançada
        try{
            cadastroService.realizarAlocacao(alocacao);
            this.alocacao = new Alocacao();
        } catch (RuntimeException e){
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção:", e.getMessage()));
        }
        return null; 
    }

    // Getters e Setters para o objeto de alocação e as listas de salas, disciplinas e usuários
    public Alocacao getAlocacao() { return alocacao; }
    public void setAlocacao(Alocacao alocacao) { this.alocacao = alocacao; }
    
    public List<Sala> getListaSalas() { return listaSalas; }
    public List<Disciplina> getListaDisciplinas() { return listaDisciplinas; }
    public List<Usuario> getListaUsuarios() { return listaUsuarios; }
}
