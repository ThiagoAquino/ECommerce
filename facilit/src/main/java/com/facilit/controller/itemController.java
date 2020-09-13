package com.facilit.controller;

import com.facilit.model.Item;
import com.facilit.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class itemController {

    @Autowired
    private itemRepository ir;


    @RequestMapping(value = "/item/{itemCode}", method = RequestMethod.GET)
    public Item itemOnly(@PathVariable("itemCode") long itemCode) {
        Item item = ir.findByItemCode(itemCode);
        return item;
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public Iterable<Item> itemList() {
        Iterable<Item> items = ir.findAll();
        return items;
    }

    @RequestMapping(value ="item/{itemCode}", method = RequestMethod.DELETE)
    public Item itemDelete(@PathVariable("itemCode") long itemCode){
        Item item = ir.findByItemCode(itemCode);
        ir.delete(item);
        return item;
    }
}
