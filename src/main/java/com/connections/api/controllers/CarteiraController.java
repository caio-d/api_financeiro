package com.connections.api.controllers;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.domain.carteira.CarteiraResponse;
import com.connections.api.services.CarteiraService;
import com.connections.api.utils.CarteiraUtils;
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
    public ResponseEntity<Carteira> insert(@RequestBody CarteiraResponse carteiraResponse) {
        Carteira carteira = new Carteira(carteiraResponse);
        service.save(carteira);
        return ResponseEntity.ok().body(carteira);
    }

    @GetMapping
    public ResponseEntity<List<Carteira>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> getById(@PathVariable Long id) {
        Optional<Carteira> carteira = service.findById(id);
        return ResponseEntity.ok(carteira.orElseThrow());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carteira> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Carteira> patch(@PathVariable Long id, @RequestBody CarteiraResponse carteiraResponse) {

        Carteira carteira = service.patch(id, carteiraResponse);

        if (carteira != null) {
            return ResponseEntity.ok(carteira);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Carteira> put(@PathVariable Long id, @RequestBody CarteiraResponse carteiraResponse) {

        CarteiraUtils utils = new CarteiraUtils();
        if (utils.putInvalido(carteiraResponse)) return ResponseEntity.notFound().build();

        Carteira carteira = service.put(id, carteiraResponse);
        return ResponseEntity.ok(carteira);
    }

}
