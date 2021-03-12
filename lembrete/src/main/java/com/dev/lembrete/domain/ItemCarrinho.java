package com.dev.lembrete.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "items_carrinho")
public class ItemCarrinho
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_carrinho_id")
    private Long itemCarrinhoId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "item_carrinho_item")
    private Item itemCarrinho;

    @NotNull
    @Column(name = "item_carrinho_item_qtd")
    private Integer itemQtd;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_carrinho_carrinho_id")
    private CarrinhoCliente carrinhoCliente;

    public ItemCarrinho() {
    }

    public ItemCarrinho(Long itemCarrinhoId, @NotNull Item itemCarrinho, @NotNull Integer itemQtd, CarrinhoCliente carrinhoCliente) {
        this.itemCarrinhoId = itemCarrinhoId;
        this.itemCarrinho = itemCarrinho;
        this.itemQtd = itemQtd;
        this.carrinhoCliente = carrinhoCliente;
    }

    public ItemCarrinho(Item item, Integer itemQtd){
        this.itemCarrinho = item;
        this.itemQtd = itemQtd;
    }

    public Long getItemCarrinhoId() {
        return itemCarrinhoId;
    }

    public void setItemCarrinhoId(Long itemCarrinhoId) {
        this.itemCarrinhoId = itemCarrinhoId;
    }

    public Item getItemCarrinho() {
        return itemCarrinho;
    }

    public void setItemCarrinho(Item itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }

    public Integer getItemQtd() {
        return itemQtd;
    }

    public void setItemQtd(Integer itemQtd) {
        this.itemQtd = itemQtd;
    }

    public CarrinhoCliente getCarrinhoCliente() {
        return carrinhoCliente;
    }

    public void setCarrinhoCliente(CarrinhoCliente carrinhoCliente) {
        this.carrinhoCliente = carrinhoCliente;
    }
}
