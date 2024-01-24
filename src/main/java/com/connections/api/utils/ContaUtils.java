package com.connections.api.utils;

import com.connections.api.domain.conta.ContaResponse;

public class ContaUtils {

    public Boolean putInvalido(ContaResponse contaResponse) {
        return contaResponse.nome() == null || contaResponse.saldo() == null || contaResponse.divida() == null || contaResponse.email() == null;
    }

}
