package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
}