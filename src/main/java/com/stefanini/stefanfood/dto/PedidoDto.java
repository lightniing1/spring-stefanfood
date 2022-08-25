package com.stefanini.stefanfood.dto;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class PedidoDto {

    private String cliente;
    private String empresa;
    private EnderecoClienteDto enderecoCliente;
    private Set<ItemDto> alimentos;
    private BigDecimal total;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PedidoDto() {

    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public EnderecoClienteDto getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(EnderecoClienteDto enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public Set<ItemDto> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Set<ItemDto> alimentos) {
        this.alimentos = alimentos;
    }
}
