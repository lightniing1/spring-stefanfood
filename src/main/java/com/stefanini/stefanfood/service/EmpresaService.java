package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.EmpresaDto;
import com.stefanini.stefanfood.model.Empresa;
import com.stefanini.stefanfood.repository.EmpresaRepository;
import org.apache.coyote.Response;
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
                .filter(empresa -> !empresa.isExcluido())
                .map(this::converteEntityParaDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<Empresa> editaDadosDaEmpresa(Empresa novaEmpresa, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        if (empresa != null) {
            empresa.setNomeFantasia(novaEmpresa.getNomeFantasia());
            empresa.setEmail(novaEmpresa.getEmail());
            empresa.setEndereco(novaEmpresa.getEndereco());
            empresa.setNumeroEndereco(novaEmpresa.getNumeroEndereco());
            empresa.setCep(novaEmpresa.getCep());
            empresaRepository.save(empresa);
            return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    /*
    public ResponseEntity<Empresa> excluirEmpresa(Long empresaId) {
        empresaRepository.deleteById(empresaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */

    public ResponseEntity<Empresa> excluirEmpresa(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        if (empresa != null) {
            empresa.setExcluido(true);
            empresaRepository.save(empresa);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public EmpresaDto converteEntityParaDto(Empresa empresa) {
        EmpresaDto empresaDto = new EmpresaDto();
        //empresaDto.setId(empresa.getId());
        empresaDto.setCnpj(empresa.getCnpj());
        empresaDto.setNome(empresa.getNomeFantasia());

        return empresaDto;
    }

}
