package com.connections.connection.Controllers;

public class ResourceNotFoundException extends RuntimeException  {

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

}
