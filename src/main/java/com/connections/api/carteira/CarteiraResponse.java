package com.connections.api.carteira;

import lombok.NonNull;
import java.time.LocalDate;

public record CarteiraResponse(Long id, Long conta_id, String nome, String tipo, Double saldo, Double divida, LocalDate vencimento) {

    public CarteiraResponse(@NonNull Carteira carteira) {
        this(carteira.getId(), carteira.getConta_id(), carteira.getNome(), carteira.getTipo(), carteira.getSaldo(), carteira.getDivida(), carteira.getVencimento());
    }

}
