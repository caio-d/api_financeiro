package com.connections.api.controllers;

import com.connections.api.domain.conta.Conta;
import com.connections.api.domain.conta.exceptions.ContaNotFoundException;
import com.connections.api.repositories.ContaRepository;
import com.connections.api.domain.conta.ContaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/conta")
public class ContaController {

    // repositoy é o JPA

    @Autowired
    private ContaRepository repository;
    // autowired -> remove a necessidade de criar manualmente instâncias
    // de objetos que sua classe depende.

    @PostMapping
    public void insertConta(@RequestBody ContaResponse contaResponse) {
        repository.save(new Conta(contaResponse));
    }

    @GetMapping
    public List<ContaResponse> getAll() {
        return repository.findAll().stream().map(ContaResponse::new).toList();
    }

    @GetMapping("/{conta_id}")
    public ResponseEntity<Conta> getOne(@PathVariable Long conta_id) {
        Conta conta = repository.findById(conta_id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada com o ID: " + conta_id));
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping
    public void deleteContaById(Long conta_id) {
        repository.deleteById(conta_id);
    }

    @PutMapping("{conta_id}")
    public ResponseEntity<ContaResponse> updateConta(@PathVariable Long conta_id, @RequestBody ContaResponse contaResponse) {

        Conta conta = repository.findById(conta_id).orElse(null);

        if (conta != null) {

            conta.setNome(contaResponse.nome());
            conta.setSaldo(contaResponse.saldo());
            conta.setDivida(contaResponse.divida());
            conta.setEmail(contaResponse.email());

            repository.save(conta);

            return ResponseEntity.ok(contaResponse);
        }

        return ResponseEntity.notFound().build();
    }

}
