package com.connections.api.domain.carteira;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "tb_carteira")
@Entity(name = "carteira")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long conta_id;
    private String nome;
    private Double saldo;
    private Double divida;
    private LocalDate vencimento;
    private Long meses_restantes;

    public Carteira(CarteiraResponse carteiraResponse) {
        this.conta_id = carteiraResponse.conta_id();
        this.nome = carteiraResponse.nome();
        this.saldo = carteiraResponse.saldo();
        this.divida = carteiraResponse.divida();
        this.vencimento = carteiraResponse.vencimento();
        this.meses_restantes = carteiraResponse.meses_restantes();
    }

}
