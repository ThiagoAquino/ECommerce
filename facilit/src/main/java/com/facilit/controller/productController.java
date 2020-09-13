package com.facilit.controller;

import com.facilit.model.Product;
import com.facilit.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class productController {

    @Autowired
    private productRepository pdr;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public @ResponseBody
    Product productSave(@RequestBody Product product) throws Exception {
        try {
            pdr.save(product);
            return product;
        } catch (Exception e) {
            throw new Exception("Check the product");
        }
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Iterable<Product> productList() {
        Iterable<Product> products = pdr.findAll();
        return products;
    }

    @RequestMapping(value = "/products/{productCode}", method = RequestMethod.GET)
    public Product productOnly(@PathVariable("productCode") long productCode) throws Exception {
        try {
            Product product = pdr.findByProductCode(productCode);
            return product;
        } catch (Exception e) {
            throw new Exception("Check the product code");
        }
    }

    @RequestMapping(value = "/products/{productCode}", method = RequestMethod.DELETE)
    public Product productDelete(@PathVariable("productCode") long productCode) throws Exception {
        try {
            Product product = pdr.findByProductCode(productCode);
            pdr.delete(product);
            return product;
        } catch (Exception e) {
           throw new Exception("Check the product code");
         }
    }

    @RequestMapping(value = "/products/{productCode}", method = RequestMethod.PUT)
    public @ResponseBody
    Product productUpdate(@PathVariable("productCode") long productCode, @Valid @RequestBody Product product) throws Exception {
        try {
            Product prod = pdr.findByProductCode(productCode);
            prod.setPriceProduct(product.getPriceProduct());
            prod.setType(product.getType());
            pdr.save(prod);
            return prod;
        } catch (Exception e) {
            throw new Exception("Check the product code");
        }
    }

    protected Product productOnlyType(String type) {
        Product product = pdr.findByType(type);
        return product;
    }
}
