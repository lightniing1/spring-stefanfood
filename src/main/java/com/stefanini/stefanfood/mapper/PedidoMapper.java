package com.stefanini.stefanfood.mapper;

import com.stefanini.stefanfood.model.Item;
import com.stefanini.stefanfood.model.PedidosItens;

import java.util.List;

public class PedidoMapper {

    private Long cliente;
    private Long empresa;
    private Long enderecoCliente;
    private List<ItemMapper> itens;

    public PedidoMapper() {

    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }

    public Long getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(Long enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public List<ItemMapper> getItens() {
        return itens;
    }

    public void setItens(List<ItemMapper> itens) {
        this.itens = itens;
    }
}
