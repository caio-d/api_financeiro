package com.connections.api.domain.carteira.exceptions;

public class CarteiraNotFoundException extends RuntimeException  {

    public CarteiraNotFoundException(String mensagem) {
        super(mensagem);
    }

}
