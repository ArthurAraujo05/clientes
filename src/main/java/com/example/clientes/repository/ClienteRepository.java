package com.example.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clientes.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}