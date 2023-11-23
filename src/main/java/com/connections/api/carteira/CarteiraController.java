package com.connections.api.carteira;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/{carteira_id}")
    public ResponseEntity<Carteira> getOne(@PathVariable Long carteira_id) {
        Carteira carteira = repository.findById(carteira_id)
                .orElseThrow(() -> new CarteiraNotFoundException("Carteira não encontrada com o ID: " + carteira_id));
        return ResponseEntity.ok(carteira);
    }

    @DeleteMapping
    public void deleteContaById(Long carteira_id) {
        repository.deleteById(carteira_id);
    }

    @PutMapping("{carteira_id}")
    public ResponseEntity<CarteiraResponse> updateCarteira(@PathVariable Long carteira_id, @RequestBody CarteiraResponse carteiraResponse) {

        Carteira carteira = repository.findById(carteira_id).orElse(null);

        if (carteira != null) {

            carteira.setConta_id(carteiraResponse.conta_id());
            carteira.setNome(carteiraResponse.nome());
            carteira.setTipo(carteiraResponse.tipo());
            carteira.setSaldo(carteiraResponse.saldo());
            carteira.setDivida(carteiraResponse.divida());
            carteira.setVencimento(carteiraResponse.vencimento());
            carteira.setMeses_restantes(carteiraResponse.meses_restantes());

            repository.save(carteira);

            return ResponseEntity.ok(carteiraResponse);
        }

        return ResponseEntity.notFound().build();
    }

}
