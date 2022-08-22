package com.stefanini.stefanfood.dto;

import antlr.LexerSharedInputState;

import java.util.List;

public class PedidoDto {

    private Long cliente;
    private Long empresa;
    private Long enderecoCliente;
    private List<Long> alimentos;

    public PedidoDto() {

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

    public List<Long> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Long> alimentos) {
        this.alimentos = alimentos;
    }
}
