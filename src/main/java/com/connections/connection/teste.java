package com.connections.connection;

import com.connections.connection.Controllers.ContaResponseDTO;
import com.connections.connection.conta.Conta;

public class teste {

    public static void main(String[] args) {

        Conta conta = new Conta(new ContaResponseDTO(new Conta()));

        System.out.println(conta.getId());


    }


}
