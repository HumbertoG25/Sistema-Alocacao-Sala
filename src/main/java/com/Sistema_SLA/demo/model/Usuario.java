package com.Sistema_SLA.demo.model;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Long perfilId;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, Long perfilId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfilId = perfilId;
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

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getSenha() {
        return senha;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }
    public Long getPerfilId() {
        return perfilId;
    }
}
