package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.*;
import com.stefanini.stefanfood.mapper.PedidoMapper;
import com.stefanini.stefanfood.model.*;
import com.stefanini.stefanfood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
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
    ItemRepository itemRepository;
    @Autowired
    ClienteService clienteService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    ItemService itemService;
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

    /*public ResponseEntity<PedidoDto> montaPedido (PedidoMapper novoPedido) {

        Cliente cliente = clienteRepository.findById(novoPedido.getCliente()).get();
        Empresa empresa = empresaRepository.findById(novoPedido.getEmpresa()).get();
        EnderecoCliente enderecoCliente = enderecoClienteRepository.findById(novoPedido.getEnderecoCliente()).get();


        if (cliente != null && empresa != null && enderecoCliente != null) {
            Pedido pedido = new Pedido(cliente, empresa, enderecoCliente);
            for (Long idAlimento : novoPedido.getAliments()) {
                Item item = itemRepository.findById(idAlimento).get();
                pedido.adicionaAlimento(item);
            }
            pedidoRepository.save(pedido);
            return new ResponseEntity<>(converteEntityParaDto(pedido), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

         */
    //}

    public PedidoDto converteEntityParaDto(Pedido pedido) {
        PedidoDto pedidoDto = new PedidoDto();
        //Chamar outros DTOs assim não parece ser uma boa prática... verificar depois

        //ItemDto
        /*List<Item> itemList = pedido.getItem().stream().toList();
        List<ItemDto> itemDtoList = new LinkedList<>();

        for (Item item : itemList) {
            ItemDto itemDto = new ItemDto();
            itemDto.setNome(item.getNome());
            itemDto.setDescricao(item.getDescricao());
            itemDto.setPreco(item.getPreco());
            itemDtoList.add(itemDto);
        }

         */

        //EnderecoClienteDto
        EnderecoClienteDto enderecoClienteDto = new EnderecoClienteDto();
        enderecoClienteDto.setEndereco(pedido.getEnderecoEntrega().getEndereco());
        enderecoClienteDto.setNumero(pedido.getEnderecoEntrega().getNumeroEndereco());
        enderecoClienteDto.setComplemento(pedido.getEnderecoEntrega().getComplemento());
        enderecoClienteDto.setCep(pedido.getEnderecoEntrega().getCep());


        pedidoDto.setCliente(pedido.getCliente().getNomeSocial());
        //pedidoDto.setAlimentos(new HashSet<>(itemDtoList));
        pedidoDto.setEmpresa(pedido.getEmpresa().getNomeFantasia());
        pedidoDto.setEnderecoCliente(enderecoClienteDto);

        return pedidoDto;
    }

}
