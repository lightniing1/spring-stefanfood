package com.stefanini.stefanfood.controller;

import com.stefanini.stefanfood.dto.ClienteDto;
import com.stefanini.stefanfood.dto.PedidoDto;
import com.stefanini.stefanfood.model.Cliente;
import com.stefanini.stefanfood.model.EnderecoCliente;
import com.stefanini.stefanfood.service.ClienteService;
import com.stefanini.stefanfood.service.EnderecoClienteService;
import com.stefanini.stefanfood.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    PedidoService pedidoService;

    @Autowired
    EnderecoClienteService enderecoClienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoDto>> listaPedidosDoCliente(@PathVariable Long id) {
        return pedidoService.listaPedidosDoCliente(id);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastraNovoCliente(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> editaDadosCliente(@RequestBody Cliente cliente,
                                                     @PathVariable Long clienteId) {
        return clienteService.editaCliente(cliente, clienteId);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Cliente> excluirCliente(@PathVariable Long clienteId) {
        return clienteService.excluirCliente(clienteId);
    }


    @PostMapping
    @RequestMapping("/{clienteId}/endereco")
    public ResponseEntity<EnderecoCliente> cadastraEnderecoCliente(@PathVariable Long clienteId,
                                                                   @RequestBody EnderecoCliente novoEndereco){
       return enderecoClienteService.criaNovoEndereco(clienteId, novoEndereco);
    }

}
