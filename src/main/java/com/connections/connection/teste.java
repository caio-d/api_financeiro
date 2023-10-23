package com.connections.connection;

import com.connections.connection.ContaController.ContaResponse;
import com.connections.connection.ContaController.Conta;

public class teste {

    public static void main(String[] args) {

        Conta conta = new Conta(new ContaResponse(new Conta()));

        System.out.println(conta.getId());


    }


}
