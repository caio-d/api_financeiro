package com.connections.api.conta;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_conta")
@Entity(name = "conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "conta_id")
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conta_id;

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
