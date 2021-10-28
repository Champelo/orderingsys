package com.bakery.orderingsys.controller;

import com.bakery.orderingsys.model.Item;
import com.bakery.orderingsys.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems(){
        return itemService.getAllItems();
    }

    @PostMapping
    public void createNewItem(@RequestBody Item item) {
        itemService.addNewItem(item);
    }

    @DeleteMapping(path ="{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PutMapping(path="{itemId}")
    @Transactional
    public void updateItemName(@PathVariable("itemId") Long itemId,
                               @RequestParam(required = false) String itemName,
                               @RequestParam(required = false) Double itemPrice) {
        itemService.updateItem(itemId, itemName, itemPrice);
    }

}
