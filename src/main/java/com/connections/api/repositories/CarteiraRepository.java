package com.connections.api.repositories;

import com.connections.api.domain.carteira.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}
