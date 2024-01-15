package com.connections.api.domain.conta.exceptions;

public class ContaNotFoundException extends RuntimeException  {

    public ContaNotFoundException(String mensagem) {
        super(mensagem);
    }

}
