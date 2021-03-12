package com.dev.lembrete.domain;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carrinho_cliente")
public class CarrinhoCliente
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JoinColumn(name = "carrinho_id")
    private Long carrinhoId;

    @OneToOne
    @JoinColumn(name = "carrinho_user_id")
    private User carrinho_user;

    //@ManyToMany
    //@JoinTable(name = "items_carrinho", joinColumns = @JoinColumn(name = "item_carrinho_carrinho_id"), inverseJoinColumns = @JoinColumn(name = "item_carrinho_item_id"))
    @Nullable
    @OneToMany
    @JoinColumn(name = "item_carrinho_id")
    private List<ItemCarrinho> itemCarrinhoList;

    @Column(name = "carrinho_valor_total")
    private Double carrinhoValorTotal;

    public CarrinhoCliente() {
    }

    public CarrinhoCliente(List<ItemCarrinho> itemCarrinhoList, @NotNull Double carrinhoValorTotal) {

        this.itemCarrinhoList = itemCarrinhoList;
        this.carrinhoValorTotal = carrinhoValorTotal;
    }

    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public User getCarrinho_user() {
        return carrinho_user;
    }

    public void setCarrinho_user(User carrinho_user) {
        this.carrinho_user = carrinho_user;
    }

    public Double getCarrinhoValorTotal() {
        return carrinhoValorTotal;
    }

    public void setCarrinhoValorTotal(Double carrinhoValorTotal) {
        this.carrinhoValorTotal = carrinhoValorTotal;
    }

    public List<ItemCarrinho> getItemCarrinhoList() {
        return itemCarrinhoList;
    }

    public void setItemCarrinhoList(List<ItemCarrinho> itemCarrinhoList) {
        this.itemCarrinhoList = itemCarrinhoList;
    }
}
