package com.facilit.repository;

import com.facilit.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface itemRepository extends CrudRepository<Item, Long> {
    Item findByItemCode(long itemCode);

}
