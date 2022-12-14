package com.stefanini.stefanfood.controller;

import com.stefanini.stefanfood.dto.PedidoDto;
import com.stefanini.stefanfood.mapper.PedidoMapper;
import com.stefanini.stefanfood.model.Pedido;
import com.stefanini.stefanfood.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDto> novoPedido (@RequestBody PedidoMapper novoPedido) {
        return pedidoService.montaPedido(novoPedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> listaPedidos() {
        return pedidoService.listaPedidos();
    }

}
