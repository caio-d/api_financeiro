package com.connections.api.controllers;

import com.connections.api.domain.conta.Conta;
import com.connections.api.domain.conta.ContaResponse;
import com.connections.api.services.ContaService;
import com.connections.api.utils.ContaUtils;
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
    public ResponseEntity<Conta> insert(@RequestBody ContaResponse contaResponse) {
        Conta conta = new Conta(contaResponse);
        service.save(conta);
        return ResponseEntity.ok().body(conta);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Long id) {
        Conta conta = service.findById(id).orElse(null);
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContaResponse> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Conta> patch(@PathVariable Long id, @RequestBody ContaResponse contaResponse) {

        Conta conta = service.patch(id, contaResponse);

        if (conta != null) {
            return ResponseEntity.ok(conta);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Conta> put(@PathVariable Long id, @RequestBody ContaResponse contaResponse) {

        ContaUtils utils = new ContaUtils();
        if (utils.putValido(contaResponse)) return ResponseEntity.notFound().build();

        Conta conta = service.put(id, contaResponse);
        return ResponseEntity.ok(conta);
    }

}
