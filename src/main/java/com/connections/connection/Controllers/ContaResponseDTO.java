package com.connections.connection.Controllers;

import com.connections.connection.conta.Conta;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;


public record ContaResponseDTO(Long id, String nome, Double saldo, Double divida) {

    // record cria getter, setter, construtor, etc
    // usado majoritariamente para itens de transferencia de dados

    public ContaResponseDTO(@NonNull @NotNull Conta conta) {
        this(conta.getId(), conta.getNome(), conta.getSaldo(), conta.getDivida());
    }

}
