package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.AlimentoDto;
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
        alimento.setExcluido(false);
        alimentoRepository.save(alimento);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<AlimentoDto>> listaTodosOsAlimentosPorEmpresa (Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        return new ResponseEntity<List<AlimentoDto>>(alimentoRepository.findAllByEmpresa(empresa)
                .stream()
                .filter(alimento -> !alimento.isExcluido())
                .map(this::convertEntitytoDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<AlimentoDto> desativaAlimentoDaEmpresa (Long empresaId, Long alimentoId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        Alimento alimento = alimentoRepository.findAlimentoByEmpresaAndId(empresa, alimentoId);
        if (alimento != null) {
            alimento.setExcluido(true);
            alimentoRepository.save(alimento);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private AlimentoDto convertEntitytoDto (Alimento alimento) {
        AlimentoDto alimentoDto = new AlimentoDto();
        alimentoDto.setNome(alimento.getNome());
        alimentoDto.setDescricao(alimento.getDescricao());
        alimentoDto.setPreco(alimento.getPreco());
        return alimentoDto;
    }

}
