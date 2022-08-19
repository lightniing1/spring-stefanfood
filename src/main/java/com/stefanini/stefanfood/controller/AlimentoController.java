package com.stefanini.stefanfood.controller;

import com.stefanini.stefanfood.model.Alimento;
import com.stefanini.stefanfood.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {

    @Autowired
    AlimentoService alimentoService;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Alimento> cadastraNovoAlimentoDaEmpresa(@RequestBody Alimento novoAlimento,
                                                                  @PathVariable Long empresaId) {
        return alimentoService.cadastraNovoAlimentoPorEmpresa(novoAlimento, empresaId);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<List<Alimento>> listaTodosOsAlimentosDaEmpresa(@PathVariable Long empresaId) {
        return alimentoService.listaTodosOsAlimentosPorEmpresa(empresaId);
    }

}
