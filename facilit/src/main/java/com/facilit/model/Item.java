package com.facilit.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemCode;

    @NotBlank
    private int qtd;

    @NotBlank
    private boolean wdiscount;


    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Product product;

    public Item(Product product, int qtd){
        this.product = product;
        this.qtd = qtd;
    }

    public long getItemCode() {
        return itemCode;
    }

    public void setItemCode(long itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void increaseQuantity(int qtd) {
        setQtd(getQtd() + qtd);
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isWdiscount() {
        return wdiscount;
    }

    public void setWdiscount(boolean wdiscount) {
        this.wdiscount = wdiscount;
    }
}
