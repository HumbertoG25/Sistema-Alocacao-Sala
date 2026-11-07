package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.model.Sala;
import com.Sistema_SLA.demo.service.CadastroService;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;

@Named
@ViewScoped
public class SalaBean implements Serializable {

    @Autowired
    private CadastroService cadastroService;

    private Sala sala = new Sala();

    public String salvarSala() {
        cadastroService.cadastrarSala(sala);
        this.sala = new Sala();
        return null;
    }

    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }
}