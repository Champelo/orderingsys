package com.bakery.orderingsys.item;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
       return itemRepository.findAll();
    }

    public Item getItem(Long itemId){
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (!itemOptional.isPresent()) {
            throw new IllegalStateException("Item doesn't exist");
        }

        Item item = itemRepository.getById(itemId);
        return item;
    }

    public void addNewItem(Item item) {
        Optional<Item> itemOptional = itemRepository.findItemByItemName(item.getItemName());
        if (itemOptional.isPresent()) {
            throw new IllegalStateException("This item is already in the system");
        }
        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        boolean exists = itemRepository.existsById(itemId);
        if (!exists) {
            throw new IllegalStateException("Item with id " + itemId + " doesn't exist");
        }
        itemRepository.deleteById(itemId);
    }

    public void updateItem(Long itemId, String itemName, Double price) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException(
                        "item with id " + itemId + " does not exist"
                ));
        if (itemName != null && itemName.length() > 0 &&
        !Objects.equals(item.getItemName(), itemName)) {
            item.setItemName(itemName);
            itemRepository.save(item);
        }

        if (price != null && price > 0){
            item.setItemPrice(price);
            itemRepository.save(item);
        }

    }
}
