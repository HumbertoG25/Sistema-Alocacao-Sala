package com.Sistema_SLA.demo.model;

public class Sala {
    private Long id;
    private String nome;
    private String bloco;
    private String tipo;

    public Sala() {
    }

    public Sala(Long id, String nome, String bloco, String tipo) {
        this.id = id;
        this.nome = nome;
        this.bloco = bloco;
        this.tipo = tipo;
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

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }
    public String getBloco() {
        return bloco;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
}
