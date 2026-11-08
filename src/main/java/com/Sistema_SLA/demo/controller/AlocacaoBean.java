package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.dao.AlocacaoDao;
import com.Sistema_SLA.demo.dao.DisciplinaDao;
import com.Sistema_SLA.demo.dao.SalaDao;
import com.Sistema_SLA.demo.dao.UsuarioDao;
import com.Sistema_SLA.demo.model.Alocacao;
import com.Sistema_SLA.demo.model.Disciplina;
import com.Sistema_SLA.demo.model.Sala;
import com.Sistema_SLA.demo.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AlocacaoBean {
    @Autowired
    private AlocacaoDao alocacaoDao;
    
    @Autowired
    private SalaDao salaDao;
    
    @Autowired
    private DisciplinaDao disciplinaDao;
    
    @Autowired
    private UsuarioDao usuarioDao;

    private Alocacao alocacao = new Alocacao();
    
    private List<Sala> listaSalas;
    private List<Disciplina> listaDisciplinas;
    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void init() {
        listaSalas = salaDao.listar();
        listaDisciplinas = disciplinaDao.listar();
        listaUsuarios = usuarioDao.listar();
    }

    public String salvarAlocacao() {
        alocacaoDao.inserir(alocacao);
        this.alocacao = new Alocacao(); 
        return null; 
    }

    public Alocacao getAlocacao() { return alocacao; }
    public void setAlocacao(Alocacao alocacao) { this.alocacao = alocacao; }
    
    public List<Sala> getListaSalas() { return listaSalas; }
    public List<Disciplina> getListaDisciplinas() { return listaDisciplinas; }
    public List<Usuario> getListaUsuarios() { return listaUsuarios; }
}
