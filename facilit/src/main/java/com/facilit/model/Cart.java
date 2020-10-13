package com.facilit.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartCode;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<Item>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> coupons = new ArrayList<Coupon>();

    public Cart(){};


    public long getCartCode() {
        return cartCode;
    }

    public void setCartCode(long cartCode) {
        this.cartCode = cartCode;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }


    public Product addProduct(Product product, int qtd){
        Item item = items.parallelStream().filter(i -> i.getProduct().equals(product)).findFirst().orElse(null);
        if(item == null){
            item = new Item(product, qtd);
        } else {
            item.increaseQuantity(qtd);
        }
        return product;
    }

    public Coupon addCoupon(Coupon coupon){
        coupons.add(coupon);
        return coupon;
    }

    public BigDecimal getcartPriceTotal() {
        return cartPriceTotal;
    }

    public void setcartPriceTotal(BigDecimal cartPriceTotal) {
        this.cartPriceTotal = cartPriceTotal;
    }

    public BigDecimal priceRefresh() {
        boolean hasCoupon = coupons.isEmpty();
        double couponValue = (1 - applyCoupon());

        if (getItem().isEmpty()) {
            setCartPrice(BigDecimal.valueOf(0));
            return BigDecimal.valueOf(0);
        } else {
            BigDecimal value = BigDecimal.valueOf(0);
            cartPriceTotal = BigDecimal.valueOf(0);
            for (Item it : this.item) {
                cartPriceTotal = cartPriceTotal.add(it.getProduct().getPriceProduct().multiply(BigDecimal.valueOf(it.getQtd())));
                if (it.getQtd() >= 10 && !it.isWdiscount()) {
                        it.getProduct().setPriceProduct(it.getProduct().getPriceProduct().multiply(BigDecimal.valueOf(0.9)));
                        it.setWdiscount(true);
                    }
                value = value.add(it.getProduct().getPriceProduct().multiply(BigDecimal.valueOf(it.getQtd())));
            }


            if (value.intValue() >= 1000 && value.intValue() < 5000) {
                value = value.multiply(new BigDecimal(0.95));
                discount = 0.05;
            } else if (value.intValue() >= 5000 && value.intValue() < 10000) {
                value = value.multiply(new BigDecimal(0.93));
                discount = 0.07;
            } else if (value.intValue() >= 10000) {
                value = value.multiply(new BigDecimal(0.9));
                discount = 0.1;
            }
            if (hasCoupon) {
                return value;
            } else {
                value = value.multiply(new BigDecimal(couponValue));
                return value;
            }
        }
    }

    private double applyCoupon() {
        double maior = 0;
        for (Coupon coupon : this.coupons) {
            if (coupon.getDiscount() > maior) {
                maior = coupon.getDiscount();
            }
        }
        return maior;
    }
}



