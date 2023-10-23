package com.connections.connection.ContaController;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "conta")
@Entity(name = "conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double saldo;

    private Double divida;

    public Conta(ContaResponse contaDTO) {
        this.nome = contaDTO.nome();
        this.saldo = contaDTO.saldo();
        this.divida = contaDTO.divida();
    }

}
