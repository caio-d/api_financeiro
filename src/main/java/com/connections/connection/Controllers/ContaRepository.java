package com.connections.connection.Controllers;

import com.connections.connection.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
