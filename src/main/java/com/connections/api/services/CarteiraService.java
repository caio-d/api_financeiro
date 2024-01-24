package com.connections.api.services;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.domain.carteira.CarteiraResponse;
import com.connections.api.domain.carteira.exceptions.CarteiraNotFoundException;
import com.connections.api.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteiraService {

    private final CarteiraRepository repository;

    @Autowired
    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.repository = carteiraRepository;
    }

    public Optional<Carteira> findById(Long id) {
        return this.repository.findById(id).map(Optional::of).orElseThrow(() -> new CarteiraNotFoundException("Carteira não encontrada com o ID: " + id));
    }

    public void save(Carteira carteira) {
        this.repository.save(carteira);
    }

    public List<Carteira> getAll() {
        return this.repository.findAll();
    }

    public void deleteById(Long carteira_id) {
        this.repository.deleteById(carteira_id);
    }

    public Carteira patch(Long id, CarteiraResponse carteiraResponse) {

        Carteira carteira = this.findById(id).orElse(null);
        if (carteira == null) return null;

        if (carteiraResponse.nome() != null) carteira.setNome(carteiraResponse.nome());
        if (carteiraResponse.saldo() != null) carteira.setSaldo(carteiraResponse.saldo());
        if (carteiraResponse.divida() != null) carteira.setDivida(carteiraResponse.divida());
        if (carteiraResponse.vencimento() != null) carteira.setVencimento(carteiraResponse.vencimento());
        if (carteiraResponse.meses_restantes() != null) carteira.setMeses_restantes(carteiraResponse.meses_restantes());

        this.save(carteira);

        return carteira;
    }

    public Carteira put(Long id, CarteiraResponse carteiraResponse) {

        Carteira carteira = this.findById(id).orElse(null);
        if (carteira == null) return null;

        carteira.setNome(carteiraResponse.nome());
        carteira.setSaldo(carteiraResponse.saldo());
        carteira.setDivida(carteiraResponse.divida());
        carteira.setVencimento(carteiraResponse.vencimento());
        carteira.setMeses_restantes(carteiraResponse.meses_restantes());

        this.save(carteira);
        return carteira;
    }

}
