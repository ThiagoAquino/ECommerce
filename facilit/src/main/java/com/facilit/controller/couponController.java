package com.facilit.controller;

import com.facilit.model.Coupon;
import com.facilit.repository.couponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class couponController {

    @Autowired
    private couponRepository cpr;

    @RequestMapping(value = "/coupons", method = RequestMethod.POST)
    public @ResponseBody
    Coupon couponSave(@RequestBody Coupon coupon) throws Exception {
        try{
            cpr.save(coupon);
            return coupon;
        } catch (Exception e) {
            throw new Exception("Check the coupon");
        }

    }

    @RequestMapping(value = "/coupons", method = RequestMethod.GET)
    public Iterable<Coupon> couponList() {
        Iterable<Coupon> coupons = cpr.findAll();
        return coupons;
    }

    @RequestMapping(value = "/coupons/{couponCode}", method = RequestMethod.GET)
    public Coupon couponOnly(@PathVariable("couponCode") long couponCode) throws Exception {
        try {
            Coupon coupon = cpr.findByCouponCode(couponCode);
            return coupon;
        } catch (Exception e) {
            throw new Exception("Check the coupon code");
        }

    }

    @RequestMapping(value = "/coupons/{couponCode}", method = RequestMethod.DELETE)
    public Coupon couponDelete(@PathVariable("couponCode") long couponCode) throws Exception {
        try{
            Coupon coupon = cpr.findByCouponCode(couponCode);
            cpr.delete(coupon);
            return coupon;
        } catch (Exception e) {
            throw new Exception("Check the coupon code");
        }
    }

    @RequestMapping(value = "/coupons/{couponCode}", method = RequestMethod.PUT)
    public @ResponseBody
    Coupon couponUpdate(@PathVariable("couponCode") long couponCode,@Valid
    @RequestBody Coupon coupon) throws Exception {
        try {
        Coupon coup = cpr.findByCouponCode(couponCode);
        coup.setDiscount(coupon.getDiscount());
        cpr.save(coup);
        return coup;
        } catch (Exception e) {
            throw new Exception("Check the coupon");
        }
    }

}
