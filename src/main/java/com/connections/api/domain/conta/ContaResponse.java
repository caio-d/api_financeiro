package com.connections.api.domain.conta;

import lombok.NonNull;

public record ContaResponse(Long id, String nome, Double saldo, Double divida, String email) {

    public ContaResponse(@NonNull Conta conta) {
        this(conta.getConta_id(), conta.getNome(), conta.getSaldo(), conta.getDivida(), conta.getEmail());
    }

}
