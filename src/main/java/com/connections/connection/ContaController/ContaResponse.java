package com.connections.connection.ContaController;

import lombok.NonNull;


public record ContaResponse(Long id, String nome, Double saldo, Double divida) {

    // record cria getter, setter, construtor, etc
    // usado majoritariamente para itens de transferencia de dados

    public ContaResponse(@NonNull Conta conta) {
        this(conta.getId(), conta.getNome(), conta.getSaldo(), conta.getDivida());
    }

}
