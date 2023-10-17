package com.connections.connection.Controllers;

import com.connections.connection.conta.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    // repositoy é o JPA

    @Autowired
    private ContaRepository repository;
    // autowired -> remove a necessidade de criar manualmente instâncias
    // de objetos que sua classe depende.

    @PutMapping
    public void SaveConta(@RequestBody ContaResponseDTO contaDTO) {
        repository.save(new Conta(contaDTO));
    }

    @GetMapping
    public List<ContaResponseDTO> getAll() {
        return repository.findAll().stream().map(ContaResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getOne(@PathVariable Long id) {
        Conta conta = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com o ID: " + id));
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ContaResponseDTO> updateSaldo(@PathVariable Long id, @RequestBody ContaResponseDTO contaDTO) {

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
