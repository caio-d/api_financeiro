package com.connections.connection.controllers.CarteiraController;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Table(name = "carteira")
@Entity(name = "carteira")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carteira {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long conta_id;

    private String nome;

    private String tipo;

    private Double saldo;

    private Double divida;

    private LocalDate vencimento;

    public Carteira(CarteiraResponse carteiraResponse) {
        this.conta_id = carteiraResponse.conta_id();
        this.tipo = carteiraResponse.tipo();
        this.nome = carteiraResponse.nome();
        this.saldo = carteiraResponse.saldo();
        this.divida = carteiraResponse.divida();
        this.vencimento = carteiraResponse.vencimento();
    }

}
