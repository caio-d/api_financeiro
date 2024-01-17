package com.connections.api.testeImplementacao;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.domain.carteira.CarteiraResponse;
import com.connections.api.domain.carteira.exceptions.CarteiraNotFoundException;
import com.connections.api.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    @Autowired
    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    public Optional<Carteira> findById(Long id) {
        return this.carteiraRepository.findById(id).map(Optional::of).orElseThrow(() -> new CarteiraNotFoundException("Carteira não encontrada com o ID: " + id));
    }

    public void save(Carteira carteira) {
        this.carteiraRepository.save(carteira);
    }

    public List<CarteiraResponse> getAll() {
        return this.carteiraRepository.findAll().stream().map(CarteiraResponse::new).toList();
    }

    public void deleteById(Long carteira_id) {
        this.carteiraRepository.deleteById(carteira_id);
    }

}
