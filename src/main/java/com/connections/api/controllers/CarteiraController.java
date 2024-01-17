package com.connections.api.controllers;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.domain.carteira.CarteiraResponse;
import com.connections.api.testeImplementacao.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    @Autowired
    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping
    public ResponseEntity<CarteiraResponse> insert(@RequestBody CarteiraResponse carteiraResponse) {
        carteiraService.save(new Carteira(carteiraResponse));
        return ResponseEntity.ok().body(carteiraResponse);
    }

    @GetMapping
    public ResponseEntity<List<CarteiraResponse>> getAll() {
        return ResponseEntity.ok().body(carteiraService.getAll());
    }

    @GetMapping("/{carteira_id}")
    public ResponseEntity<Carteira> getById(@PathVariable Long carteira_id) {
        Optional<Carteira> carteira = carteiraService.findById(carteira_id);
        return ResponseEntity.ok(carteira.orElseThrow());
    }

    @DeleteMapping
    public ResponseEntity<CarteiraResponse> deleteById(Long carteira_id) {
        carteiraService.deleteById(carteira_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{carteira_id}")
    public ResponseEntity<CarteiraResponse> updateById(@PathVariable Long carteira_id, @RequestBody CarteiraResponse carteiraResponse) {

        Carteira carteira = carteiraService.findById(carteira_id).orElse(null);

        if (carteira != null) {

            if (carteiraResponse.nome().isEmpty()) carteira.setNome(carteiraResponse.nome());
            if (carteiraResponse.saldo() != null) carteira.setSaldo(carteiraResponse.saldo());
            if (carteiraResponse.divida() != null) carteira.setDivida(carteiraResponse.divida());
            if (carteiraResponse.vencimento() != null) carteira.setVencimento(carteiraResponse.vencimento());
            if (carteiraResponse.meses_restantes() != null) carteira.setMeses_restantes(carteiraResponse.meses_restantes());

            carteiraService.save(carteira);

            return ResponseEntity.ok(carteiraResponse);
        }

        return ResponseEntity.notFound().build();
    }

}
