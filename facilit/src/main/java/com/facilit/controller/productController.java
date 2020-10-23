package com.facilit.controller;

import com.facilit.model.Product;
import com.facilit.repository.productRepository;
import com.facilit.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class productController {

    @Autowired
    private productService ps;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public @ResponseBody
    Product productSave(@RequestBody Product product) {
        Product prod = ps.productSave(product);
        return prod;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Iterable<Product> productList() {
        Iterable<Product> products = ps.productList();
        return products;
    }

    @RequestMapping(value = "/products/{productCode}", method = RequestMethod.GET)
    public Product productOnly(@PathVariable("productCode") long productCode) {
        Product product = ps.productOnly(productCode);
        return product;
    }

    @RequestMapping(value = "/products/{productCode}", method = RequestMethod.DELETE)
    public Product productDelete(@PathVariable("productCode") long productCode) throws Exception {
        Product product = ps.productDelete(productCode);
        return product;
    }


    @RequestMapping(value = "/products/{productCode}", method = RequestMethod.PUT)
    public @ResponseBody
    Product productUpdate(@PathVariable("productCode") long productCode, @Valid @RequestBody Product product) throws Exception {
        Product prod = ps.productUpdate(productCode, product);
        return prod;
    }
}
