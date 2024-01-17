package com.connections.api.testeImplementacao;

import com.connections.api.domain.carteira.Carteira;
import com.connections.api.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    @Autowired
    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    public Optional<Carteira> findById(Long id) {
        return this.carteiraRepository.findById(id);
    }

}
