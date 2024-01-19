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

    private final com.connections.api.repositories.ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository ContaRepository) {
        this.contaRepository = ContaRepository;
    }

    public Optional<Conta> findById(Long id) {
        return this.contaRepository.findById(id).map(Optional::of).orElseThrow(() -> new ContaNotFoundException("Conta não encontrada com o ID: " + id));
    }

    public void save(Conta conta) {
        this.contaRepository.save(conta);
    }

    public List<ContaResponse> getAll() {
        return this.contaRepository.findAll().stream().map(ContaResponse::new).toList();
    }

    public void deleteById(Long Conta_id) {
        this.contaRepository.deleteById(Conta_id);
    }

}
