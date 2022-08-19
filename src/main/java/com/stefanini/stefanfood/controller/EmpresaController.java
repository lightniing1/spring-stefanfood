package com.stefanini.stefanfood.controller;

import com.stefanini.stefanfood.dto.EmpresaDto;
import com.stefanini.stefanfood.model.Empresa;
import com.stefanini.stefanfood.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> cadastraNovaEmpresa(@RequestBody Empresa novaEmpresa) {
        return empresaService.cadastraNovaEmpresa(novaEmpresa);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> listaTodasAsEmpresas() {
        return empresaService.listaTodasAsEmpresas();

    }

}
