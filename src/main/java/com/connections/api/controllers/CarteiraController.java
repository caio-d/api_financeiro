package com.connections.api.controllers;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.domain.carteira.CarteiraResponse;
import com.connections.api.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/carteira")
public class CarteiraController {

    private final CarteiraService service;

    @Autowired
    public CarteiraController(CarteiraService carteiraService) {
        this.service = carteiraService;
    }

    @PostMapping
    public ResponseEntity<CarteiraResponse> insert(@RequestBody CarteiraResponse carteiraResponse) {
        service.save(new Carteira(carteiraResponse));
        return ResponseEntity.ok().body(carteiraResponse);
    }

    @GetMapping
    public ResponseEntity<List<CarteiraResponse>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> getById(@PathVariable Long id) {
        Optional<Carteira> carteira = service.findById(id);
        return ResponseEntity.ok(carteira.orElseThrow());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarteiraResponse> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<CarteiraResponse> patch(@PathVariable Long id, @RequestBody CarteiraResponse carteiraResponse) {

        Carteira carteira = service.findById(id).orElse(null);

        if (carteira != null) {

            if (carteiraResponse.nome() != null) carteira.setNome(carteiraResponse.nome());
            if (carteiraResponse.saldo() != null) carteira.setSaldo(carteiraResponse.saldo());
            if (carteiraResponse.divida() != null) carteira.setDivida(carteiraResponse.divida());
            if (carteiraResponse.vencimento() != null) carteira.setVencimento(carteiraResponse.vencimento());
            if (carteiraResponse.meses_restantes() != null) carteira.setMeses_restantes(carteiraResponse.meses_restantes());

            service.save(carteira);

            return ResponseEntity.ok(carteiraResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CarteiraResponse> put(@PathVariable Long id, @RequestBody CarteiraResponse carteiraResponse) {

        Carteira carteira = service.findById(id).orElse(null);

            if (carteira != null && !carteiraResponse.nome().isEmpty() && carteiraResponse.saldo() != null && carteiraResponse.divida() != null &&
                    carteiraResponse.vencimento() != null && carteiraResponse.meses_restantes() != null) {

                carteira.setNome(carteiraResponse.nome());
                carteira.setSaldo(carteiraResponse.saldo());
                carteira.setDivida(carteiraResponse.divida());
                carteira.setVencimento(carteiraResponse.vencimento());
                carteira.setMeses_restantes(carteiraResponse.meses_restantes());

                service.save(carteira);
                return ResponseEntity.ok(carteiraResponse);
            }

        return ResponseEntity.notFound().build();
    }

}
