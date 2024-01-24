package com.connections.api.utils;

import com.connections.api.domain.carteira.CarteiraResponse;

public class CarteiraUtils {

    public Boolean putInvalido(CarteiraResponse carteiraResponse) {
        return carteiraResponse.nome() == null || carteiraResponse.saldo() == null || carteiraResponse.divida() == null ||
                carteiraResponse.vencimento() == null || carteiraResponse.meses_restantes() == null;
    }

}
