package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}