package com.connections.connection.Controllers;

import com.connections.connection.conta.Conta;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void SaveConta(@RequestBody ContaResponseDTO contaDTO) {
        repository.save(new Conta(contaDTO));
    }

    @GetMapping
    public List<ContaResponseDTO> getAll() {
        return repository.findAll().stream().map(ContaResponseDTO::new).toList();
    }

    @DeleteMapping
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/saldo")
    public void updateSaldo(Long id, Double novoSaldo) {
        Conta conta = repository.findById(id).orElse(null);
        if (conta != null) {
            conta.setSaldo(novoSaldo);
            repository.save(conta);
        }
    }

    @PostMapping("/divida")
    public void updateDivida(Long id, Double novaDivida) {
        Conta conta = repository.findById(id).orElse(null);
        if (conta != null) {
            conta.setDivida(novaDivida);
            repository.save(conta);
        }
    }

}
