package com.stefanini.stefanfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class PedidosItens implements Serializable {

    @EmbeddedId
    PedidoItemPK id = new PedidoItemPK();
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @MapsId("pedidoId")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @MapsId("itemId")
    private Item item;

    Integer quantidade;

    public PedidoItemPK getId() {
        return id;
    }

    public void setId(PedidoItemPK id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}