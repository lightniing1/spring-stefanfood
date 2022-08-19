package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.model.Alimento;
import com.stefanini.stefanfood.model.Empresa;
import com.stefanini.stefanfood.repository.AlimentoRepository;
import com.stefanini.stefanfood.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlimentoService {

    @Autowired
    AlimentoRepository alimentoRepository;
    @Autowired
    EmpresaRepository empresaRepository;

    public ResponseEntity<Alimento> cadastraNovoAlimentoPorEmpresa (Alimento alimento, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        alimento.setEmpresa(empresa);
        alimentoRepository.save(alimento);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<Alimento>> listaTodosOsAlimentosPorEmpresa (Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        return new ResponseEntity<List<Alimento>>(alimentoRepository.findAllByEmpresa(empresa), HttpStatus.OK);
    }

}
