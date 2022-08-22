package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.model.*;
import com.stefanini.stefanfood.repository.EnderecoClienteRepository;
import com.stefanini.stefanfood.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    EnderecoClienteRepository enderecoClienteRepository;

    private Pedido prePedido (Cliente cliente, Empresa empresa) {
        EnderecoCliente endereco = enderecoClienteRepository.findByCliente(cliente);
        if (endereco != null) {
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setEmpresa(empresa);
            pedido.setEnderecoEntrega(endereco);
            return pedido;
        }
        return null;
    }

    public ResponseEntity<Pedido> criaPedido (Cliente cliente, Empresa empresa, List<Alimento> alimentos) {
        Pedido pedido = prePedido(cliente, empresa);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
