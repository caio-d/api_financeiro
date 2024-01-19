package com.connections.api.controllers;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.domain.carteira.CarteiraResponse;
import com.connections.api.domain.conta.Conta;
import com.connections.api.domain.conta.exceptions.ContaNotFoundException;
import com.connections.api.domain.conta.ContaResponse;
import com.connections.api.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/conta")
public class ContaController {

    private final ContaService service;
    @Autowired
    public ContaController(ContaService repository) {
        this.service = repository;
    }

    @PostMapping
    public ResponseEntity<ContaResponse> insert(@RequestBody ContaResponse contaResponse) {
        service.save(new Conta(contaResponse));
        return ResponseEntity.ok().body(contaResponse);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponse>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Long id) {
        Optional<Conta> conta = service.findById(id);
        return ResponseEntity.ok(conta.orElseThrow());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContaResponse> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ContaResponse> updateConta(@PathVariable Long id, @RequestBody ContaResponse contaResponse) {

        Conta conta = service.findById(id).orElse(null);

        if (conta != null) {

            if (contaResponse.nome().isEmpty()) conta.setNome(contaResponse.nome());
            if (contaResponse.saldo() != null) conta.setSaldo(contaResponse.saldo());
            if (contaResponse.divida() != null) conta.setDivida(contaResponse.divida());
            if (contaResponse.email().isEmpty()) conta.setEmail(contaResponse.email());

            service.save(conta);

            return ResponseEntity.ok(contaResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ContaResponse> put(@PathVariable Long id, @RequestBody ContaResponse contaResponse) {

        Conta conta = service.findById(id).orElse(null);

        if (conta != null && !contaResponse.nome().isEmpty() && contaResponse.saldo() != null && contaResponse.divida() != null &&
                !contaResponse.email().isEmpty()) {

            conta.setNome(contaResponse.nome());
            conta.setSaldo(contaResponse.saldo());
            conta.setDivida(contaResponse.divida());
            conta.setEmail(contaResponse.email());

            service.save(conta);
            return ResponseEntity.ok(contaResponse);
        }

        return ResponseEntity.notFound().build();
    }

}
