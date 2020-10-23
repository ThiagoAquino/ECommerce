package com.facilit.service;

        import com.facilit.model.Item;
        import com.facilit.repository.itemRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.web.bind.annotation.PathVariable;

@Service
public class itemService {

    @Autowired
    public itemRepository ir;


    public Item itemOnly(long itemCode) {
        Item item = ir.findByItemCode(itemCode);
        return item;
    }

    public Iterable<Item> itemList() {
        Iterable<Item> items = ir.findAll();
        return items;
    }

    public Item itemSave(Item item) {
        Item it = ir.save(item);
        return it;
    }

    public Item itemDelete(long itemCode){
        Item item = ir.findByItemCode(itemCode);
        ir.delete(item);
        return item;
    }
}
