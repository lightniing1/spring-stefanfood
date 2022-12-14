package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Cliente;
import com.stefanini.stefanfood.model.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {

    EnderecoCliente findByCliente(Cliente cliente);

}