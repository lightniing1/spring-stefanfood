package com.stefanini.stefanfood.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    //@OneToMany(mappedBy = "pedido")
    //private Set<PedidosItens> item = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private EnderecoCliente enderecoEntrega;

    public Pedido() {

    }

    public Pedido(Cliente cliente, Empresa empresa, EnderecoCliente enderecoEntrega) {
        this.cliente = cliente;
        this.empresa = empresa;
        this.enderecoEntrega = enderecoEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EnderecoCliente getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoCliente enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    /*public Set<PedidosItens> getItem() {
        return item;
    }



    public void setItem(Set<PedidosItens> item) {
        this.item = item;
    }


    public void adicionaAlimento (Item item) {
        this.alimentos.add(item);
        item.getPedidos().add(this);
    }
    */
}