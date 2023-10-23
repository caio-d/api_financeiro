package com.connections.api.carteira;

public class CarteiraNotFoundException extends RuntimeException  {

    public CarteiraNotFoundException(String mensagem) {
        super(mensagem);
    }

}
