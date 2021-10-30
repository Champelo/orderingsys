package com.bakery.orderingsys.item;

import com.bakery.orderingsys.item.Item;
import com.bakery.orderingsys.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepositoryTest;

    @Test
    void findItemByItemNameTest() {
        //given
        Item item = new Item(
                "Strawberry Shortcake",
                1.50,
                "Flour, Eggs, Strawberries, Milk, Sugar, Vanilla Extract",
                false,
                5
        );
        itemRepositoryTest.save(item);
        //when
        Optional<Item> expected = itemRepositoryTest.findItemByItemName("Strawberry Shortcake");
        //then
        assertThat(expected).isPresent();

    }
}