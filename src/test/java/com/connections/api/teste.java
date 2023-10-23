package com.connections.api;

import com.connections.api.conta.ContaResponse;
import com.connections.api.conta.Conta;

public class teste {

    public static void main(String[] args) {

        Conta conta = new Conta(new ContaResponse(new Conta()));

        System.out.println(conta.getId());


    }


}
