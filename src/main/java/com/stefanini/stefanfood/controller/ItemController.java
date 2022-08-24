package com.stefanini.stefanfood.controller;

import com.stefanini.stefanfood.dto.ItemDto;
import com.stefanini.stefanfood.model.Item;
import com.stefanini.stefanfood.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Item> cadastraNovoItemDaEmpresa(@RequestBody Item novoItem,
                                                              @PathVariable Long empresaId) {
        return itemService.cadastraNovoItemPorEmpresa(novoItem, empresaId);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<List<ItemDto>> listaTodosOsItensDaEmpresa(@PathVariable Long empresaId) {
        return itemService.listaTodosOsItensPorEmpresa(empresaId);
    }

    @PutMapping("/{empresaId}/{itemId}")
    public ResponseEntity<ItemDto> desativaItemDaEmpresa(@PathVariable Long empresaId,
                                                         @PathVariable Long itemId) {
        return itemService.desativaItemDaEmpresa(empresaId, itemId);
    }

}
