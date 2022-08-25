package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Pedido;
import com.stefanini.stefanfood.model.PedidoItemPK;
import com.stefanini.stefanfood.model.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosItensRepository extends JpaRepository<PedidosItens, PedidoItemPK> {

List<PedidosItens> findAllByPedido(Pedido pedido);

}