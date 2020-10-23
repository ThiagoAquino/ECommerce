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

    private BigDecimal itemValue;


    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Product product;

    public Item(Product product, int qtd){
        this.product = product;
        this.qtd = qtd;
    }

    public Item(){}

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

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

    //Increase Quantity
    public void increaseQuantity(int qtd) {
        setQtd(getQtd() + qtd);
    }

    //Calculate this item value
    public void setItemValue(){
        BigDecimal var = new BigDecimal(qtd + "");
        if(qtd >= 10){
            setValue((getProduct().getPriceProduct().multiply(var)).multiply(new BigDecimal("0.10")));
        } else {
            setValue(getProduct().getPriceProduct().multiply(var));
        }
    }
}
