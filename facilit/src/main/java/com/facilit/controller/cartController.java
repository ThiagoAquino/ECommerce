package com.facilit.controller;

import com.facilit.model.*;
import com.facilit.repository.*;
import com.facilit.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class cartController {

    @Autowired
    private cartRepository cr;

    @Autowired
    private cartService cs;


    @RequestMapping(value = "/carts/{cartCode}", method = RequestMethod.GET)
    public Cart cartOnly(@PathVariable("cartCode") long cartCode) {
        Cart cart = cs.cartOnly(cartCode);
        return cart;
    }

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public Iterable<Cart> cartList() {
        Iterable<Cart> carts = cs.cartList();
        return carts;
    }

    @RequestMapping(value = "/carts/{cartCode}", method = RequestMethod.POST)
    public @ResponseBody
    Cart addProductIntoCart(@PathVariable("cartCode") long cartCode, @Valid @RequestBody Item item) throws Exception {

        Cart cart = cs.addProduct(cartCode, item);
       /*
       Cart cart = cr.findByCartCode(cartCode);
        User user = cart.getUser();
        Product product = pr.findByProductCode(item.getProduct().getProductCode());
        if (product != null) {
                cart.getItem().add(item);
                item.setProduct(product);
            cart.setCartPrice(cart.priceRefresh());
            cr.save(cart);
            ir.save(item);
        } else {
            throw new Exception("Product not registered");
        }
        */
        return cart;
    }

    @RequestMapping(value ="carts/{cartCode}", method = RequestMethod.DELETE)
    public Cart cartDelete(@PathVariable("cartCode") long cartCode){
        Cart cart = cs.cartDelete(cartCode);
        return cart;
    }


    @RequestMapping(value = "/cart/{cartCode}", method = RequestMethod.POST)
    public @ResponseBody void
    addCouponIntoCart(@PathVariable("cartCode") long cartCode, @Valid @RequestBody Coupon coupon) throws Exception {
        /*
        try {
            Cart cart = cr.findByCartCode(cartCode);
            Coupon coupon1 = cpr.findByCouponCode(coupon.getCouponCode());
            cart.getCoupons().add(coupon1);
            cart.setCartPrice(cart.priceRefresh());
            cr.save(cart);
            return cart;
        } catch (Exception e) {
            throw new Exception("Check the coupon or the cart code");
        }

         */

    }


    protected void cartDelete(User user){

    }
}
