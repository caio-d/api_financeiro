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

    @Autowired
    private ContaRepository repository;

    @PostMapping
    public void insertConta(@RequestBody ContaResponse contaResponse) {
        repository.save(new Conta(contaResponse));
    }

    @GetMapping
    public ResponseEntity<List<ContaResponse>> getAll() {
        List<ContaResponse> contas = repository.findAll().stream().map(ContaResponse::new).toList();
        return ResponseEntity.ok().body(contas);
    }

    @GetMapping("/{conta_id}")
    public ResponseEntity<Conta> getById(@PathVariable Long conta_id) {
        Conta conta = repository.findById(conta_id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada com o ID: " + conta_id));
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping
    public ResponseEntity<ContaResponse> deleteContaById(Long conta_id) {
        repository.deleteById(conta_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{conta_id}")
    public ResponseEntity<ContaResponse> updateConta(@PathVariable Long conta_id, @RequestBody ContaResponse contaResponse) {

        Conta conta = repository.findById(conta_id).orElse(null);

        if (conta != null) {

            if (contaResponse.nome().isEmpty()) conta.setNome(contaResponse.nome());
            if (contaResponse.saldo() != null) conta.setSaldo(contaResponse.saldo());
            if (contaResponse.divida() != null) conta.setDivida(contaResponse.divida());
            if (contaResponse.email().isEmpty()) conta.setEmail(contaResponse.email());

            repository.save(conta);

            return ResponseEntity.ok(contaResponse);
        }

        return ResponseEntity.notFound().build();
    }

}
