package com.Sistema_SLA.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema_SLA.demo.dao.AlocacaoDao;
import com.Sistema_SLA.demo.dao.DisciplinaDao;
import com.Sistema_SLA.demo.dao.SalaDao;
import com.Sistema_SLA.demo.dao.UsuarioDao;
import com.Sistema_SLA.demo.model.Alocacao;
import com.Sistema_SLA.demo.model.Disciplina;
import com.Sistema_SLA.demo.model.Sala;
import com.Sistema_SLA.demo.model.Usuario;

@Service
public class CadastroService {
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private SalaDao salaDao;
    
    @Autowired
    private DisciplinaDao disciplinaDao;
    
    @Autowired
    private AlocacaoDao alocacaoDao;

    public void cadastrarUsuario(Usuario usuario) {
        usuarioDao.inserir(usuario);
    }

    public void cadastrarSala(Sala sala) {
        salaDao.inserir(sala);
    }

    public void cadastrarDisciplina(Disciplina disciplina) {
        disciplinaDao.inserir(disciplina);
    }

    public void realizarAlocacao(Alocacao alocacao) {
        alocacaoDao.inserir(alocacao);
    }
}
