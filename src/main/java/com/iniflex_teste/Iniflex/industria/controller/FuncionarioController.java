package com.iniflex_teste.Iniflex.industria.controller;

import com.iniflex_teste.Iniflex.industria.model.Funcionario;
import com.iniflex_teste.Iniflex.industria.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
  private final FuncionarioService service;

  public FuncionarioController(FuncionarioService service) {
    this.service = service;
  }

  @GetMapping
  public List<Funcionario> listarFuncionarios() {
    return service.getFuncionarios();
  }

  @PostMapping
  public ResponseEntity<String> adicionarFuncionario(@RequestBody Funcionario funcionario) {
    System.out.println("Funcionario recebido: " + funcionario);
    service.adicionar(funcionario);
    return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário adicionado com sucesso");
  }

  @PutMapping("/{nome}")
  public ResponseEntity<String> atualizarFuncionario(@PathVariable String nome, @RequestBody Funcionario funcionarioAtualizado) {
    boolean atualizado = service.atualizarPorNome(nome, funcionarioAtualizado);
    if (atualizado) {
      return ResponseEntity.ok("Funcionário atualizado com sucesso");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
    }
  }

  @DeleteMapping("/{nome}")
  public ResponseEntity<String> removerFuncionario(@PathVariable String nome) {
    boolean removido = service.removerPorNome(nome);
    if (removido) {
      return ResponseEntity.ok("Funcionário removido com sucesso");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
    }
  }
}

