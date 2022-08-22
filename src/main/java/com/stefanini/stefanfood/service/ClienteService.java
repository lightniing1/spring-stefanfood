package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.ClienteDto;
import com.stefanini.stefanfood.dto.EmpresaDto;
import com.stefanini.stefanfood.model.Cliente;
import com.stefanini.stefanfood.model.Empresa;
import com.stefanini.stefanfood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity<ClienteDto> cadastraNovoCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return new ResponseEntity<ClienteDto>(converteEntityParaDto(cliente), HttpStatus.CREATED);
    }

    public ResponseEntity<List<ClienteDto>> listarClientes() {
        return new ResponseEntity<List<ClienteDto>>(clienteRepository.findAll()
                .stream()
                .map(this::converteEntityParaDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private ClienteDto converteEntityParaDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNomeSocial(cliente.getNomeSocial());
        clienteDto.setEmail(cliente.getEmail());
        return clienteDto;
    }

}
