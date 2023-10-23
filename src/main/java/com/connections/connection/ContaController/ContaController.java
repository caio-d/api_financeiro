package com.connections.connection.ContaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    // repositoy é o JPA

    @Autowired
    private ContaRepository repository;
    // autowired -> remove a necessidade de criar manualmente instâncias
    // de objetos que sua classe depende.

    @PostMapping
    public void insertConta(@RequestBody ContaResponse contaDTO) {
        repository.save(new Conta(contaDTO));
    }

    @GetMapping
    public List<ContaResponse> getAll() {
        return repository.findAll().stream().map(ContaResponse::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getOne(@PathVariable Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada com o ID: " + id));
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping
    public void deleteContaById(Long id) {
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ContaResponse> updateConta(@PathVariable Long id, @RequestBody ContaResponse contaDTO) {

        Conta conta = repository.findById(id).orElse(null);

        if (conta != null) {

            conta.setNome(contaDTO.nome());
            conta.setSaldo(contaDTO.saldo());
            conta.setDivida(contaDTO.divida());

            repository.save(conta);

            return ResponseEntity.ok(contaDTO);
        }

        return ResponseEntity.notFound().build();
    }

}
