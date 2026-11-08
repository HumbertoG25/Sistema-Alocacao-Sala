package com.Sistema_SLA.demo.controller;

import com.Sistema_SLA.demo.dao.AlocacaoDao;
import com.Sistema_SLA.demo.dao.DisciplinaDao;
import com.Sistema_SLA.demo.dao.SalaDao;
import com.Sistema_SLA.demo.dao.UsuarioDao;
import com.Sistema_SLA.demo.model.Alocacao;
import com.Sistema_SLA.demo.model.Disciplina;
import com.Sistema_SLA.demo.model.Sala;
import com.Sistema_SLA.demo.model.Usuario;
import com.Sistema_SLA.demo.service.CadastroService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class deleteBean implements Serializable {

    @Autowired 
    private CadastroService cadastroService;

    @Autowired private SalaDao salaDao;
    @Autowired private DisciplinaDao disciplinaDao;
    @Autowired private UsuarioDao usuarioDao;
    @Autowired private AlocacaoDao alocacaoDao;

    private List<Sala> salas;
    private List<Disciplina> disciplinas;
    private List<Usuario> usuarios;
    private List<Alocacao> alocacoes;

    @PostConstruct
    public void init() {
        carregarDados();
    }
    
    private void carregarDados() {
        salas = salaDao.listar();
        disciplinas = disciplinaDao.listar();
        usuarios = usuarioDao.listar();
        alocacoes = alocacaoDao.listar();
    }

    public void excluirSala(Long id) {
        cadastroService.excluirSala(id);
        carregarDados();
    }

    public void excluirDisciplina(Long id) {
        cadastroService.excluirDisciplina(id);
        carregarDados();
    }

    public void excluirUsuario(Long id) {
        cadastroService.excluirUsuario(id);
        carregarDados();
    }

    public void excluirAlocacao(Long id) {
        cadastroService.excluirAlocacao(id);
        carregarDados();
    }

    public List<Sala> getSalas() {
         return salas; 
        }

    public List<Disciplina> getDisciplinas() {
         return disciplinas; 
        }

    public List<Usuario> getUsuarios() {
         return usuarios; 
        }

    public List<Alocacao> getAlocacoes() {
         return alocacoes; 
        }
}
