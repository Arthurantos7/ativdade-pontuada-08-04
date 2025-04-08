package com.aula.atividade_pontuada.service;

import com.aula.atividade_pontuada.model.Funcionario;
import com.aula.atividade_pontuada.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario salvar(Funcionario funcionarioSalvar) {
        if (funcionarioRepository.existsByEmail(funcionarioSalvar.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return funcionarioRepository.save(funcionarioSalvar);
    }

    public Funcionario buscarPorId(Long id){
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("O Funcionário não foi encontrado"));
    }

    @Transactional
    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizar){
        Funcionario funcionario1 = buscarPorId(id);

        if (!funcionario1.getEmail().equals(funcionario1.getEmail()) && funcionarioRepository.existsByEmail(funcionarioAtualizar.getEmail())) {
            throw new IllegalArgumentException("o email já foi cadastrado");
        }
        funcionarioAtualizar.setId(funcionario1.getId());
        return funcionarioRepository.save(funcionarioAtualizar);
    }

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        Funcionario funcionario = buscarPorId(id);
        funcionarioRepository.delete(funcionario);
    }
}
