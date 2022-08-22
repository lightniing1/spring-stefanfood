package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.PedidoDto;
import com.stefanini.stefanfood.model.*;
import com.stefanini.stefanfood.repository.*;
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
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    AlimentoRepository alimentoRepository;


    private EnderecoCliente pegaEnderecoCliente (Cliente cliente) {
        EnderecoCliente endereco = enderecoClienteRepository.findByCliente(cliente);
        if (endereco != null) {
            return endereco;
        }
        return null;
    }

    public ResponseEntity<List<Pedido>> listaPedidos() {
        return new ResponseEntity<List<Pedido>>(pedidoRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Pedido> montaPedido (PedidoDto novoPedido) {

        Cliente cliente = clienteRepository.findById(novoPedido.getCliente()).get();
        System.out.println(cliente);
        Empresa empresa = empresaRepository.findById(novoPedido.getEmpresa()).get();
        System.out.println(empresa);
        EnderecoCliente enderecoCliente = enderecoClienteRepository.findById(novoPedido.getEnderecoCliente()).get();
        System.out.println(enderecoCliente);

        if (cliente != null && empresa != null && enderecoCliente != null) {
            Pedido pedido = new Pedido(cliente, empresa, enderecoCliente);
            for (Long idAlimento : novoPedido.getAlimentos()) {
                Alimento alimento = alimentoRepository.findById(idAlimento).get();
                pedido.adicionaAlimento(alimento);
            }
            pedidoRepository.save(pedido);
            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
