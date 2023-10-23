package com.connections.api.conta;

public class ContaNotFoundException extends RuntimeException  {

    public ContaNotFoundException(String mensagem) {
        super(mensagem);
    }

}
