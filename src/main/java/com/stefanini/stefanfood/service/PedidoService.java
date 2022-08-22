package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.*;
import com.stefanini.stefanfood.mapper.PedidoMapper;
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
    @Autowired
    ClienteService clienteService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    AlimentoService alimentoService;
    @Autowired
    EnderecoClienteService enderecoClienteService;


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

    public ResponseEntity<PedidoDto> montaPedido (PedidoMapper novoPedido) {

        Cliente cliente = clienteRepository.findById(novoPedido.getCliente()).get();
        Empresa empresa = empresaRepository.findById(novoPedido.getEmpresa()).get();
        EnderecoCliente enderecoCliente = enderecoClienteRepository.findById(novoPedido.getEnderecoCliente()).get();

        if (cliente != null && empresa != null && enderecoCliente != null) {
            Pedido pedido = new Pedido(cliente, empresa, enderecoCliente);
            for (Long idAlimento : novoPedido.getAlimentos()) {
                Alimento alimento = alimentoRepository.findById(idAlimento).get();
                pedido.adicionaAlimento(alimento);
            }
            pedidoRepository.save(pedido);
            return new ResponseEntity<>(converteEntityParaDto(pedido), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public PedidoDto converteEntityParaDto(Pedido pedido) {
        PedidoDto pedidoDto = new PedidoDto();
        //Chamar outros DTOs assim não parece ser uma boa prática...
        EmpresaDto empresaDto = new EmpresaDto();
        AlimentoDto alimentoDto = new AlimentoDto();
        EnderecoClienteDto enderecoClienteDto = new EnderecoClienteDto();

        pedidoDto.setCliente(pedido.getCliente().getNomeSocial());
        pedidoDto.setAlimentos(pedido.getAlimentos());
        pedidoDto.setEmpresa(pedido.getEmpresa().getNomeFantasia());
        pedidoDto.setEnderecoCliente(pedido.getEnderecoEntrega());
        return pedidoDto;
    }

}
