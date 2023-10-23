package com.connections.connection.controllers.CarteiraController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraRepository repository;

    @PostMapping
    public void insertCarteira(@RequestBody CarteiraResponse carteiraResponse) {
        repository.save(new Carteira(carteiraResponse));
    }

    @GetMapping
    public List<CarteiraResponse> getAll() {
        return repository.findAll().stream().map(CarteiraResponse::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> getOne(@PathVariable Long id) {
        Carteira carteira = repository.findById(id)
                .orElseThrow(() -> new CarteiraNotFoundException("Carteira não encontrada com o ID: " + id));
        return ResponseEntity.ok(carteira);
    }

    @DeleteMapping
    public void deleteContaById(Long id) {
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<CarteiraResponse> updateCarteira(@PathVariable Long id, @RequestBody CarteiraResponse carteiraResponse) {

        Carteira carteira = repository.findById(id).orElse(null);

        if (carteira != null) {

            carteira.setNome(carteiraResponse.nome());
            carteira.setTipo(carteiraResponse.tipo());
            carteira.setSaldo(carteiraResponse.saldo());
            carteira.setDivida(carteiraResponse.divida());
            carteira.setVencimento(carteiraResponse.vencimento());

            repository.save(carteira);

            return ResponseEntity.ok(carteiraResponse);
        }

        return ResponseEntity.notFound().build();
    }

}
