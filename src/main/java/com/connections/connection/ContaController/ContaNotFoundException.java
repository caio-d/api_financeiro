package com.connections.connection.ContaController;

public class ContaNotFoundException extends RuntimeException  {

    public ContaNotFoundException(String mensagem) {
        super(mensagem);
    }

}
