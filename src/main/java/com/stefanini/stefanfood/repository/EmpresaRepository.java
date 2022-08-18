package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}