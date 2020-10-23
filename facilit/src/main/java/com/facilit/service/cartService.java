package com.facilit.service;

import com.facilit.model.Cart;
import com.facilit.model.Item;
import com.facilit.model.Product;
import com.facilit.model.User;
import com.facilit.repository.cartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cartService {

    @Autowired
    public cartRepository cr;

    @Autowired
    public productService ps;

    @Autowired
    public itemService is;


    public Cart cartOnly(long cartCode) {
        Cart cart = cr.findByCartCode(cartCode);
        return cart;
    }

    public Cart addProduct(long cartCode, Item item) {
        Cart cart = cr.findByCartCode(cartCode);
        cart.addProduct(item.getProduct(), item.getQtd());
        cr.save(cart);
        return cart;
    }

    public Iterable<Cart> cartList() {
        Iterable<Cart> carts = cr.findAll();
        return carts;
    }

    public Cart cartDelete(long cartCode) {
        Cart cart = cr.findByCartCode(cartCode);
        cr.delete(cart);
        return cart;
    }


    public Cart addProductIntoCart(long cartCode, Item item) {
        Cart cart = cr.findByCartCode(cartCode);
        User user = cart.getUser();
        Product product = ps.productOnly(item.getProduct().getProductCode());
        if (product != null) {
            cart.getItems().add(item);
            item.setProduct(product);
            cr.save(cart);
            is.itemSave(item);
        }
        return cart;
    }

    public User cartCreate(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cr.save(cart);
        return user;
    }
}
