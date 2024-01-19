package com.connections.api.domain.conta;

import lombok.NonNull;

public record ContaResponse(String nome, Double saldo, Double divida, String email) {

    public ContaResponse(@NonNull Conta conta) {
        this(conta.getNome(), conta.getSaldo(), conta.getDivida(), conta.getEmail());
    }

}
