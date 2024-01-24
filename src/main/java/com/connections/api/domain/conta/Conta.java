package com.connections.api.domain.conta;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_conta")
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
    private String email;

    public Conta(ContaResponse contaResponse) {
        this.nome = contaResponse.nome();
        this.saldo = contaResponse.saldo();
        this.divida = contaResponse.divida();
        this.email = contaResponse.email();
    }

}
