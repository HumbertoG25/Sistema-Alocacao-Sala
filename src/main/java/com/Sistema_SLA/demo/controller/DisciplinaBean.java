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

    // Service responsável por cadastrar disciplinas no sistema
    @Autowired
    private CadastroService cadastroService;

    private Disciplina disciplina = new Disciplina();

    // Método chamado ao submeter o formulário de cadastro de disciplina para salvar a disciplina no banco de dados
    public String salvarDisciplina() {
        cadastroService.cadastrarDisciplina(disciplina);
        this.disciplina = new Disciplina();
        return null;
    }

    // Getters e Setters para o objeto de disciplina
    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }
}