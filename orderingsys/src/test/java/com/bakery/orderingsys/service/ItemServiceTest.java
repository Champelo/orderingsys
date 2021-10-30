package com.bakery.orderingsys.service;

import com.bakery.orderingsys.model.Item;
import com.bakery.orderingsys.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepositoryTest;
    private ItemService itemServiceTest;

    @BeforeEach
    void setUp() {
        itemServiceTest = new ItemService(itemRepositoryTest);
    }


    @Test
    void getAllItemsTest() {

        itemServiceTest.getAllItems();
        verify(itemRepositoryTest).findAll();
    }

    @Test
    void getItem() {
        Item item = new Item();
        given(itemRepositoryTest.findById(anyLong())).willReturn(Optional.of(item));
        itemServiceTest.getItem(anyLong());
        verify(itemRepositoryTest).findById(anyLong());
        verify(itemRepositoryTest).getById(anyLong());
    }

    @Test
    void getMissingItem() {
        assertThatThrownBy(() -> itemServiceTest.getItem(anyLong()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Item doesn't exist");
    }


    @Test
    void addNewItemTest() {
        //Given
        Item item = new Item(
                "Strawberry Shortcake",
                1.50,
                "Flour, Eggs, Strawberries, Milk, Sugar, Vanilla Extract",
                false,
                5
        );
        //When
        itemServiceTest.addNewItem(item);

        //Then
        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepositoryTest).save(itemArgumentCaptor.capture());
        Item capturedItem = itemArgumentCaptor.getValue();

        assertThat(capturedItem).isEqualTo(item);
    }

    @Test
    void ThrowWhenItemExists() {
        //Giver
        Item item = new Item(
                "Strawberry Shortcake",
                1.50,
                "Flour, Eggs, Strawberries, Milk, Sugar, Vanilla Extract",
                false,
                5
        );
        given(itemRepositoryTest.findItemByItemName(anyString())).willReturn(Optional.of(item));

        //When


        //Then
        assertThatThrownBy(() -> itemServiceTest.addNewItem(item))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("This item is already in the system");

        verify(itemRepositoryTest, never()).save(any());
    }

    @Test
    void deleteItemTest() {
       //Given
        given(itemRepositoryTest.existsById(anyLong())).willReturn(true);
       //When
        itemServiceTest.deleteItem(anyLong());
       //Then
        verify(itemRepositoryTest).deleteById(anyLong());
    }

    @Test
    void deleteItemMissingTest() {
        //Given

        given(itemRepositoryTest.existsById(anyLong())).willReturn(false);
        //Then
        assertThatThrownBy(() -> itemServiceTest.deleteItem(anyLong()))
                .isInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("Item with id 0 doesn't exist");
        verify(itemRepositoryTest, never()).deleteById(any());
    }

    @Test
    void updateItemNameTest() {
        //Given
        Item item = new Item(
                "Strawberry Shortcake",
                1.50,
                "Flour, Eggs, Strawberries, Milk, Sugar, Vanilla Extract",
                false,
                5
        );

        given(itemRepositoryTest.findById(anyLong())).willReturn(Optional.of(item));

        //Then
        itemServiceTest.updateItem(anyLong(), "Blueberry Pie", 0.00);
    }

    @Test
    void updateItemPriceTest() {
        //Given
        Item item = new Item(
                "Strawberry Shortcake",
                1.50,
                "Flour, Eggs, Strawberries, Milk, Sugar, Vanilla Extract",
                false,
                5
        );

        given(itemRepositoryTest.findById(anyLong())).willReturn(Optional.of(item));

        //Then
        itemServiceTest.updateItem(anyLong(), null, 5.00);
    }
}