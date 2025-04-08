package com.aula.atividade_pontuada.controller;

import com.aula.atividade_pontuada.model.Funcionario;
import com.aula.atividade_pontuada.repository.FuncionarioRepository;
import com.aula.atividade_pontuada.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Funcionario funcionario) {
        try {
            Funcionario criado = service.salvar(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Funcionário cadastrado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("cadastro não foi realizado: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Funcionario funcionario = service.buscarPorId(id);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("funcionário não encontrado: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
        try {
            Funcionario atualizado = service.atualizar(id, funcionario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Atualização do funcionário não foi feita: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            Funcionario funcionario = service.buscarPorId(id);
            service.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("o funcionário não foi deletado: " + e.getMessage());
        }
    }
}
