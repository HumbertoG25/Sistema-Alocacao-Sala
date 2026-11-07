package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.model.Disciplina;
import com.Sistema_SLA.demo.service.CadastroService;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;

@Named
@ViewScoped
public class DisciplinaBean implements Serializable {

    @Autowired
    private CadastroService cadastroService;

    private Disciplina disciplina = new Disciplina();

    public String salvarDisciplina() {
        cadastroService.cadastrarDisciplina(disciplina);
        this.disciplina = new Disciplina();
        return null;
    }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }
}