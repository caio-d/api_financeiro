package com.connections.api.carteira;

import lombok.NonNull;
import java.time.LocalDate;

public record CarteiraResponse(Long id, Long conta_id, String nome, String tipo, Double saldo, Double divida, LocalDate vencimento, Long meses_restantes) {

    public CarteiraResponse(@NonNull Carteira carteira) {
        this(carteira.getCarteira_id(), carteira.getConta_id(), carteira.getNome(), carteira.getTipo(),
                carteira.getSaldo(), carteira.getDivida(), carteira.getVencimento(), carteira.getMeses_restantes());
    }

}
