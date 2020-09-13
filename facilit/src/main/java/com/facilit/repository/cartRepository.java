package com.facilit.repository;

import com.facilit.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface cartRepository extends CrudRepository<Cart, Long> {
    Cart findByCartCode(long cartCode);
}
