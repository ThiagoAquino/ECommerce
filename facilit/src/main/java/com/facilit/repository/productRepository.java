package com.facilit.repository;

import com.facilit.model.Cart;
import com.facilit.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface productRepository extends CrudRepository<Product, Long> {
    Product findByProductCode(long productCode);
    Product findByType(String type);
}
