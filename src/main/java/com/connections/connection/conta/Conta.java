package com.connections.connection.conta;

import com.connections.connection.Controllers.ContaResponseDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "conta")
@Entity(name = "conta")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double saldo;

    private Double divida;

    public Conta(ContaResponseDTO contaDTO) {
        this.nome = contaDTO.nome();
        this.saldo = contaDTO.saldo();
        this.divida = contaDTO.divida();
    }

    public Conta(String nome, Double saldo, Double divida) {
        this.nome = nome;
        this.saldo = saldo;
        this.divida = divida;
    }

}
