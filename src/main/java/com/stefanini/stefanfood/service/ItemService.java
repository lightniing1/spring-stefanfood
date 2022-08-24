package com.stefanini.stefanfood.service;

import com.stefanini.stefanfood.dto.ItemDto;
import com.stefanini.stefanfood.model.Item;
import com.stefanini.stefanfood.model.Empresa;
import com.stefanini.stefanfood.repository.ItemRepository;
import com.stefanini.stefanfood.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EmpresaRepository empresaRepository;

    public ResponseEntity<Item> cadastraNovoItemPorEmpresa(Item item, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        item.setEmpresa(empresa);
        item.setExcluido(false);
        itemRepository.save(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<ItemDto>> listaTodosOsItensPorEmpresa(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        return new ResponseEntity<List<ItemDto>>(itemRepository.findAllByEmpresa(empresa)
                .stream()
                .filter(alimento -> !alimento.isExcluido())
                .map(this::convertEntitytoDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<ItemDto> desativaItemDaEmpresa(Long empresaId, Long alimentoId) {
        Empresa empresa = empresaRepository.findById(empresaId).get();
        Item item = itemRepository.findItemByEmpresaAndId(empresa, alimentoId);
        if (item != null) {
            item.setExcluido(true);
            itemRepository.save(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ItemDto convertEntitytoDto (Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setNome(item.getNome());
        itemDto.setDescricao(item.getDescricao());
        itemDto.setPreco(item.getPreco());
        return itemDto;
    }

}
