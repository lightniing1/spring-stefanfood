package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.EmpresaDto;
import com.stefanini.stefanfood.model.Empresa;
import com.stefanini.stefanfood.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public ResponseEntity<Empresa> cadastraNovaEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<EmpresaDto>> listaTodasAsEmpresas() {
        return new ResponseEntity<List<EmpresaDto>>(empresaRepository.findAll()
                .stream()
                .map(this::converteEntityParaDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private EmpresaDto converteEntityParaDto(Empresa empresa) {
        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setId(empresa.getId());
        empresaDto.setCnpj(empresa.getCnpj());
        empresaDto.setNome(empresa.getNome());

        return empresaDto;
    }

}
