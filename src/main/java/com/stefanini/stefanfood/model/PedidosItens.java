package com.stefanini.stefanfood.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PedidosItens implements Serializable {

    @EmbeddedId
    PedidoItemPK id;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @MapsId("pedidoId")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @MapsId("itemId")
    private Item item;

    Integer quantidade;

}