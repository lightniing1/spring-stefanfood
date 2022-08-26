package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.*;
import com.stefanini.stefanfood.mapper.ItemMapper;
import com.stefanini.stefanfood.mapper.PedidoMapper;
import com.stefanini.stefanfood.model.*;
import com.stefanini.stefanfood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    PedidosItensRepository pedidosItensRepository;
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


    public ResponseEntity<List<PedidoDto>> listaPedidos() {
        return new ResponseEntity<List<PedidoDto>>(pedidoRepository.findAll().stream()
                .map(this::converteEntityParaDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<PedidoDto> montaPedido (PedidoMapper novoPedido) {

        Cliente cliente = clienteRepository.findById(novoPedido.getCliente()).get();
        Empresa empresa = empresaRepository.findById(novoPedido.getEmpresa()).get();
        EnderecoCliente enderecoCliente = enderecoClienteRepository.findById(novoPedido.getEnderecoCliente()).get();

        if (cliente != null && empresa != null && enderecoCliente != null) {
            Pedido pedido = new Pedido(cliente, empresa, enderecoCliente);
            pedidoRepository.save(pedido);

            for (ItemMapper item : novoPedido.getItens()) {
                Item item_requisitado_repository = itemRepository.findById(item.getItemId()).get();

                PedidoItemPK pedidoItemPK = new PedidoItemPK();
                pedidoItemPK.setPedidoId(pedido.getId());
                pedidoItemPK.setItemId(item_requisitado_repository.getId());

                PedidosItens pedidoItem = new PedidosItens();
                pedidoItem.setId(pedidoItemPK);
                pedidoItem.setPedido(pedido);
                pedidoItem.setItem(item_requisitado_repository);
                pedidoItem.setQuantidade(item.getQuantidade());

                pedidosItensRepository.save(pedidoItem);
            }

            return new ResponseEntity<>(converteEntityParaDto(pedido), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<PedidoDto>> listaPedidosDoCliente(Long clientePedido) {
        Cliente cliente = clienteRepository.findById(clientePedido).get();
        return new ResponseEntity<List<PedidoDto>>(pedidoRepository.findAll().stream()
                .filter(x -> x.getCliente().equals(cliente))
                .map(this::converteEntityParaDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public PedidoDto converteEntityParaDto(Pedido pedido) {
        PedidoDto pedidoDto = new PedidoDto();
        //Chamar outros DTOs assim não parece ser uma boa prática... verificar depois

        List<ItemDto> itemDtoList = new ArrayList<>();

        //ItemDto
        for (PedidosItens item : pedidosItensRepository.findAllByPedido(pedido).stream().toList()) {
            ItemDto itemDto = new ItemDto();
            itemDto.setNome(item.getItem().getNome());
            itemDto.setDescricao(item.getItem().getDescricao());
            itemDto.setPreco(item.getItem().getPreco());
            itemDto.setQuantidade(item.getQuantidade());
            itemDtoList.add(itemDto);
        }

        //EnderecoClienteDto
        EnderecoClienteDto enderecoClienteDto = new EnderecoClienteDto();
        enderecoClienteDto.setEndereco(pedido.getEnderecoEntrega().getEndereco());
        enderecoClienteDto.setNumero(pedido.getEnderecoEntrega().getNumeroEndereco());
        enderecoClienteDto.setComplemento(pedido.getEnderecoEntrega().getComplemento());
        enderecoClienteDto.setCep(pedido.getEnderecoEntrega().getCep());

        pedidoDto.setCliente(pedido.getCliente().getNomeSocial());
        pedidoDto.setEmpresa(pedido.getEmpresa().getNomeFantasia());
        pedidoDto.setEnderecoCliente(enderecoClienteDto);
        pedidoDto.setAlimentos(new HashSet<>(itemDtoList));

        BigDecimal somaTotal = itemDtoList.stream()
                .map(itemDto -> BigDecimal.valueOf(itemDto.getQuantidade()).multiply(itemDto.getPreco()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedidoDto.setTotal(somaTotal);

        return pedidoDto;
    }

}
