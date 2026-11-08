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

    // DAOs para acessar os dados de salas, disciplinas, usuários e alocações
    @Autowired private SalaDao salaDao;
    @Autowired private DisciplinaDao disciplinaDao;
    @Autowired private UsuarioDao usuarioDao;
    @Autowired private AlocacaoDao alocacaoDao;

    // Listas para armazenar os dados carregados do banco de dados
    private List<Sala> salas;
    private List<Disciplina> disciplinas;
    private List<Usuario> usuarios;
    private List<Alocacao> alocacoes;

    // Método chamado após a construção do bean para carregar os dados do banco de dados
    @PostConstruct
    public void init() {
        carregarDados();
    }
    
    // Método para carregar os dados de salas, disciplinas, usuários e alocações do banco de dados
    private void carregarDados() {
        salas = salaDao.listar();
        disciplinas = disciplinaDao.listar();
        usuarios = usuarioDao.listar();
        alocacoes = alocacaoDao.listar();
    }

    // Métodos para excluir salas, disciplinas, usuários e alocações,
    //  chamando o serviço de cadastro e recarregando os dados após a exclusão

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

    // Getters para as listas de salas, disciplinas, usuários e alocações,
    //  permitindo que sejam acessadas na interface do usuário
    
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
