package com.connections.api.controllers;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.domain.carteira.exceptions.CarteiraNotFoundException;
import com.connections.api.repositories.CarteiraRepository;
import com.connections.api.domain.carteira.CarteiraResponse;
import com.connections.api.testeImplementacao.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    @Autowired
    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @Autowired
    private CarteiraRepository repository;

    @GetMapping("/{carteira_id}")
    public ResponseEntity<Carteira> getByIdImplementada(@PathVariable Long carteira_id) {
        Carteira carteira = carteiraService.findById(carteira_id).orElseThrow(() -> new CarteiraNotFoundException("Carteira não mamada com o ID: " + carteira_id));
        return ResponseEntity.ok(carteira);
    }

    @PostMapping
    public ResponseEntity<CarteiraResponse> insertCarteira(@RequestBody CarteiraResponse carteiraResponse) {
        repository.save(new Carteira(carteiraResponse));
        return ResponseEntity.ok().body(carteiraResponse);
    }

    @GetMapping
    public ResponseEntity<List<CarteiraResponse>> getAll() {
        List<CarteiraResponse> carteira = repository.findAll().stream().map(CarteiraResponse::new).toList();
        return ResponseEntity.ok().body(carteira);
    }

//    @GetMapping("/{carteira_id}")
//    public ResponseEntity<Carteira> getById(@PathVariable Long carteira_id) {
//        Carteira carteira = repository.findById(carteira_id).orElseThrow(() -> new CarteiraNotFoundException("Carteira não encontrada com o ID: " + carteira_id));
//        return ResponseEntity.ok(carteira);
//    }

    @DeleteMapping
    public ResponseEntity<CarteiraResponse> deleteContaById(Long carteira_id) {
        repository.deleteById(carteira_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{carteira_id}")
    public ResponseEntity<CarteiraResponse> updateCarteira(@PathVariable Long carteira_id, @RequestBody CarteiraResponse carteiraResponse) {

        Carteira carteira = repository.findById(carteira_id).orElse(null);

        if (carteira != null) {

            if (carteiraResponse.nome().isEmpty()) carteira.setNome(carteiraResponse.nome());
            if (carteiraResponse.saldo() != null) carteira.setSaldo(carteiraResponse.saldo());
            if (carteiraResponse.divida() != null) carteira.setDivida(carteiraResponse.divida());
            if (carteiraResponse.vencimento() != null) carteira.setVencimento(carteiraResponse.vencimento());
            if (carteiraResponse.meses_restantes() != null) carteira.setMeses_restantes(carteiraResponse.meses_restantes());

            repository.save(carteira);

            return ResponseEntity.ok(carteiraResponse);
        }

        return ResponseEntity.notFound().build();
    }

}
