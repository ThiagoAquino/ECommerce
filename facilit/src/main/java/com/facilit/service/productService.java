package com.facilit.service;

import com.facilit.model.Cart;
import com.facilit.model.Item;
import com.facilit.model.Product;
import com.facilit.model.User;
import com.facilit.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class productService {

    @Autowired
    public productRepository pr;


    public Product productOnly(long productCode) {
        Product product = pr.findByProductCode(productCode);
        return product;
    }

    public Iterable<Product> productList() {
        Iterable<Product> products = pr.findAll();
        return products;
    }

    public Product productSave(Product product) {
        pr.save(product);
        return product;
    }

    public Product productDelete(long productCode) {
            Product product = pr.findByProductCode(productCode);
            pr.delete(product);
            return product;
    }

    public Product productUpdate(long productCode, Product product) {
            Product prod = pr.findByProductCode(productCode);
            prod.setPriceProduct(product.getPriceProduct());
            prod.setType(product.getType());
            pr.save(prod);
            return prod;
    }

    public Product productOnlyType(String type) {
        Product product = pr.findByType(type);
        return product;
    }

}
