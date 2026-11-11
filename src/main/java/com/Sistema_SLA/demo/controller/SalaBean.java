package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.dao.SalaDao;
import com.Sistema_SLA.demo.model.Sala;
import com.Sistema_SLA.demo.service.CadastroService;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SalaBean implements Serializable {

    // Service responsável por cadastrar salas no sistema
    @Autowired
    private CadastroService cadastroService;

    @Autowired 
    private SalaDao salaDao;

    private Sala sala = new Sala();

    private List<Sala> salas;

    @PostConstruct 
    public void init(){
        carregarSalas();
    }

    public void carregarSalas(){
        this.salas = salaDao.listar();
    }

    // Método chamado ao submeter o formulário de cadastro de sala para salvar a sala no banco de dados
    public String salvarSala() {
        cadastroService.cadastrarSala(sala);
        this.sala = new Sala();
        carregarSalas();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Sala cadastrada com sucesso!"));
        return null;
    }

    public void excluirSala(Long id) {
        try {
            cadastroService.excluirSala(id);
            carregarSalas();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Sala excluída com sucesso!"));
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção:", e.getMessage()));
        }
    }

    // Getters e Setters para o objeto de sala e para a listagem
    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }

    public List<Sala> getSalas() { return salas; }
}  