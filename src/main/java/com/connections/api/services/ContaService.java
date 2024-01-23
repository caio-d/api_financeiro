package com.connections.api.services;

import com.connections.api.domain.conta.Conta;
import com.connections.api.domain.conta.ContaResponse;
import com.connections.api.domain.conta.exceptions.ContaNotFoundException;
import com.connections.api.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository repository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.repository = contaRepository;
    }

    public Optional<Conta> findById(Long id) {
        return this.repository.findById(id).map(Optional::of).orElseThrow(() -> new ContaNotFoundException("Conta não encontrada com o ID: " + id));
    }

    public void save(Conta conta) {
        this.repository.save(conta);
    }

    public List<Conta> getAll() {
        return this.repository.findAll();
    }

    public void deleteById(Long Conta_id) {
        this.repository.deleteById(Conta_id);
    }

    public Conta patch(Long id, ContaResponse contaResponse) {

        Conta conta = this.findById(id).orElse(null);
        if (conta == null) return null;

        if (contaResponse.nome() != null) conta.setNome(contaResponse.nome());
        if (contaResponse.saldo() != null) conta.setSaldo(contaResponse.saldo());
        if (contaResponse.divida() != null) conta.setDivida(contaResponse.divida());
        if (contaResponse.email() != null) conta.setEmail(contaResponse.email());

        this.save(conta);

        return conta;
    }

    public Conta put(Long id, ContaResponse contaResponse) {

        Conta conta = this.findById(id).orElse(null);
        if (conta == null) return null;

        conta.setNome(contaResponse.nome());
        conta.setSaldo(contaResponse.saldo());
        conta.setDivida(contaResponse.divida());
        conta.setEmail(contaResponse.email());

        this.save(conta);
        return conta;
    }

}
