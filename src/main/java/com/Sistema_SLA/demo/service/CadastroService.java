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

    // Cadastra um novo usuário no banco de dados, verificando se o e-mail já está em uso
    public void cadastrarUsuario(Usuario usuario) {

        // Verifica se já existe um usuário com o mesmo e-mail no banco de dados
        if (usuarioDao.buscaEmail(usuario.getEmail()).isPresent()){
            throw new RuntimeException("Já existe um usuário cadastrado com este e-mail!");
        }

        // Insere o novo usuário no banco de dados
        usuarioDao.inserir(usuario);
    }

    // Cadastra uma nova sala no banco de dados
    public void cadastrarSala(Sala sala) {
        salaDao.inserir(sala);
    }

    // Cadastra uma nova disciplina no banco de dados
    public void cadastrarDisciplina(Disciplina disciplina) {
        disciplinaDao.inserir(disciplina);
    }

    // Realiza a alocação de uma sala para um usuário em um determinado dia e horário, verificando se há conflitos
    public void realizarAlocacao(Alocacao alocacao) {

        // Verifica se já existe uma alocação conflitante no banco de dados
        boolean conflito = alocacaoDao.existeConflitoHorario(
            alocacao.getSalaId(), 
            alocacao.getUsuarioId(), 
            alocacao.getDiaSemana(), 
            alocacao.getHorario()
        );

        // Se houver um conflito, lança uma exceção informando que a sala já está ocupada neste dia e horário
        if (conflito) {
            throw new RuntimeException("Conflito de horário: Esta sala já está ocupada neste dia e horário!");
        }
        // Insere a nova alocação no banco de dados
        alocacaoDao.inserir(alocacao);
    }

    // Exclui um usuário do banco de dados com base no ID fornecido
    public void excluirUsuario(Long id) {
        usuarioDao.deletar(id);
    }

    // Exclui uma sala do banco de dados com base no ID fornecido
    public void excluirSala(Long id) {
        salaDao.deletar(id);
    }

    // Exclui uma disciplina do banco de dados com base no ID fornecido
    public void excluirDisciplina(Long id) {
        disciplinaDao.deletar(id);
    }

    // Exclui uma alocação do banco de dados com base no ID fornecido
    public void excluirAlocacao(Long id) {
        alocacaoDao.deletar(id);
    }
}
