package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}