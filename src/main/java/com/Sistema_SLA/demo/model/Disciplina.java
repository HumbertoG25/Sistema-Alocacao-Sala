package com.Sistema_SLA.demo.model;

public class Disciplina {
    private Long id;
    private String nome;

    public Disciplina() {
    }

    public Disciplina(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
