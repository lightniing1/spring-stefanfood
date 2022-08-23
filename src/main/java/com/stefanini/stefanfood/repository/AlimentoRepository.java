package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Alimento;
import com.stefanini.stefanfood.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    List<Alimento> findAllByEmpresa(Empresa empresa);
    Alimento findAlimentoByEmpresaAndId(Empresa empresa, Long AlimentoId);
}