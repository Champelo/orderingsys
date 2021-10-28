package com.bakery.orderingsys.repository;

import com.bakery.orderingsys.model.Item;
import com.bakery.orderingsys.repository.ItemRepository;
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