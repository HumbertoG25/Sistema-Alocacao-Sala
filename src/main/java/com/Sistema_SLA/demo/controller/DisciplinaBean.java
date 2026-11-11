package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.dao.DisciplinaDao;
import com.Sistema_SLA.demo.model.Disciplina;
import com.Sistema_SLA.demo.service.CadastroService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DisciplinaBean implements Serializable {

    // Service responsável por cadastrar disciplinas no sistema
    @Autowired
    private CadastroService cadastroService;

    @Autowired 
    private DisciplinaDao disciplinaDao;

    private Disciplina disciplina = new Disciplina();

    private List<Disciplina> disciplinas;

    @PostConstruct
    public void init(){
        carregarDisciplinas();
    }

    public void carregarDisciplinas(){
        this.disciplinas = disciplinaDao.listar();
    }

    // Método chamado ao submeter o formulário de cadastro de disciplina para salvar a disciplina no banco de dados
    public String salvarDisciplina() {
        cadastroService.cadastrarDisciplina(disciplina);
        this.disciplina = new Disciplina();
        carregarDisciplinas();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Disciplina cadastrada com sucesso!"));
        return null;
    }

    public void excluirDisciplina(Long id){
        try{
            cadastroService.excluirDisciplina(id);
            carregarDisciplinas();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Disciplina excluída com sucesso!"));
        }catch(RuntimeException e){
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção:", e.getMessage()));
        }
    }

    // Getters e Setters para o objeto de disciplina e para a listagem
    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public List<Disciplina> getDisciplinas() { return disciplinas; }
}