package com.Sistema_SLA.demo.model;

public class Alocacao {
    private Long id;
    private Long usuarioId;
    private Long salaId;
    private Long disciplinaId;
    private String horario;
    private String diaSemana;
    private String usuarioNome;
    private String usuarioTipo;
    private String salaNome;
    private String salaTipo;
    private String disciplinaNome;

    public Alocacao() {
    }

    public Alocacao(Long id, Long usuarioId, Long salaId, Long disciplinaId, String horario, String diaSemana) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.salaId = salaId;
        this.disciplinaId = disciplinaId;
        this.horario = horario;
        this.diaSemana = diaSemana;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }
    public Long getSalaId() {
        return salaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getHorario() {
        return horario;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
    public String getDiaSemana() {
        return diaSemana;
    }

    public String getUsuarioNome() {
         return usuarioNome; 
        }
    public void setUsuarioNome(String usuarioNome) {
         this.usuarioNome = usuarioNome; 
        }

    public String getUsuarioTipo() { 
        return usuarioTipo; }
        
    public void setUsuarioTipo(String usuarioTipo) {
         this.usuarioTipo = usuarioTipo; 
        }

    public String getSalaNome() { 
        return salaNome; 
    }
    public void setSalaNome(String salaNome) {
         this.salaNome = salaNome; 
        }

    public String getSalaTipo() { 
        return salaTipo; 
    }
    public void setSalaTipo(String salaTipo) { 
        this.salaTipo = salaTipo; 
    }

    public String getDisciplinaNome() { 
        return disciplinaNome; 
    }
    public void setDisciplinaNome(String disciplinaNome) { 
        this.disciplinaNome = disciplinaNome; 
    }
}
