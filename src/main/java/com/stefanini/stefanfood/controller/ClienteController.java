package com.stefanini.stefanfood.controller;

import com.stefanini.stefanfood.dto.ClienteDto;
import com.stefanini.stefanfood.model.Cliente;
import com.stefanini.stefanfood.model.EnderecoCliente;
import com.stefanini.stefanfood.service.ClienteService;
import com.stefanini.stefanfood.service.EnderecoClienteService;
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
    EnderecoClienteService enderecoClienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        return clienteService.listarClientes();
    }

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastraNovoCliente(cliente);
    }

    @PostMapping
    @RequestMapping("/{clienteId}/endereco")
    public ResponseEntity<EnderecoCliente> cadastraEnderecoCliente(@PathVariable Long clienteId,
                                                                   @RequestBody EnderecoCliente novoEndereco){
       return enderecoClienteService.criaNovoEndereco(clienteId, novoEndereco);
    }

}
