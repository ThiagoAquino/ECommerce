package com.facilit.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCode;

    @NotBlank
    private BigDecimal priceProduct;

    @NotBlank
    private String type;


    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
