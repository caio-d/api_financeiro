package com.connections.api;

import com.connections.api.domain.conta.ContaResponse;
import com.connections.api.domain.conta.Conta;

public class teste {

    public static void main(String[] args) {

        Conta conta = new Conta(new ContaResponse(new Conta()));

        System.out.println(conta.getConta_id());


    }


}
