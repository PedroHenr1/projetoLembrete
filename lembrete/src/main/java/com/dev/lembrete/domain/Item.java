package com.dev.lembrete.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "items")
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @NotNull
    @Column(name = "item_nome")
    private String itemNome;

    @NotNull
    @Column(name = "item_desc")
    private String itemDesc;

    @NotNull
    @Column(name = "item_valor")
    private Float itemValor;

    public Item() {

    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNome() {
        return itemNome;
    }

    public void setItemNome(String itemNome) {
        this.itemNome = itemNome;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Float getItemValor() {
        return itemValor;
    }

    public void setItemValor(Float itemValor) {
        this.itemValor = itemValor;
    }
}
