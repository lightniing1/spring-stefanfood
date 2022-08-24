package com.stefanini.stefanfood.repository;

import com.stefanini.stefanfood.model.Item;
import com.stefanini.stefanfood.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByEmpresa(Empresa empresa);
    Item findItemByEmpresaAndId(Empresa empresa, Long AlimentoId);
}