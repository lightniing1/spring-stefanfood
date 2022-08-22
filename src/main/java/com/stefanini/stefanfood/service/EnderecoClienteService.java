package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.model.Cliente;
import com.stefanini.stefanfood.model.EnderecoCliente;
import com.stefanini.stefanfood.repository.ClienteRepository;
import com.stefanini.stefanfood.repository.EnderecoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnderecoClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoClienteRepository enderecoClienteRepository;

    public ResponseEntity<EnderecoCliente> criaNovoEndereco (Long clienteId, EnderecoCliente novoEndereco) {
        Cliente cliente = clienteRepository.findById(clienteId).get();
        novoEndereco.setCliente(cliente);
        if (cliente != null) {
            return new ResponseEntity<>(enderecoClienteRepository.save(novoEndereco), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
