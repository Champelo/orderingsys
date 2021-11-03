package com.bakery.orderingsys.item;

import com.bakery.orderingsys.orderticket.OrderTicket;
import com.bakery.orderingsys.orderticket.OrderTicketController;
import com.bakery.orderingsys.orderticket.OrderTicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemServiceTest;

    private List<Item> itemList;

    @BeforeEach
    void setUp() {
        this.itemList = new ArrayList<>();
         this.itemList.add(new Item(
                "Pumpkin Pie",
                5.99,
                "Flour Sugar, Cocoa, Eggs",
                false,
                5
        ));
        this.itemList.add(new Item(
                "Red Velvet Cupcake",
                5.99,
                "Flour Sugar, Cocoa, Eggs",
                false,
                5
        ));
    }

    @Test
    void shouldGetAllItems() throws Exception {

        given(itemServiceTest.getAllItems()).willReturn(itemList);

        this.mockMvc.perform(get("/api/v1/item"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$[*].itemId").isNotEmpty())
                .andExpect(jsonPath("$[*].itemName").isNotEmpty())
                .andExpect(jsonPath("$[*].itemPrice").isNotEmpty())
                .andExpect(jsonPath("$[*].ingredients").isNotEmpty())
                .andExpect(jsonPath("$[*].soldOut").isNotEmpty())
                .andExpect(jsonPath("$[*].quantity").isNotEmpty());
    }

    @Test
    void shouldGetItem() throws Exception {

        when(itemServiceTest.getItem(1L)).thenReturn(itemList.get(0));

        mockMvc.perform(get("/api/v1/item/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.itemId").value(itemList.get(0).getItemId()))
                .andExpect(jsonPath("$.itemName").value(itemList.get(0).getItemName()))
                .andExpect(jsonPath("$.itemPrice").value(itemList.get(0).getItemPrice()))
                .andExpect(jsonPath("$.ingredients").value(itemList.get(0).getIngredients()))
                .andExpect(jsonPath("$.soldOut").value(itemList.get(0).isSoldOut()))
                .andExpect(jsonPath("$.quantity").value(itemList.get(0).getQuantity()));
    }

    @Test
    void shouldAddNewItem() throws Exception{

        mockMvc.perform(post("/api/v1/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemList.get(0))))
                        .andExpect(status().isOk());

        verify(itemServiceTest).addNewItem(any(Item.class));
    }

    @Test
    void shouldDeleteItem() throws Exception{
        mockMvc.perform(delete("/api/v1/item/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemServiceTest).deleteItem(anyLong());
    }

    @Test
    void shouldUpdateItemName() throws Exception{
        mockMvc.perform(put("/api/v1/item/{itemId}", 1L)
                        .contentType("application/json")
                        .param("itemName", "Cake"))
                        .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateItemPrice() throws Exception{
        mockMvc.perform(put("/api/v1/item/{itemId}", 1L)
                        .contentType("application/json")
                        .param("itemPrice", "0.49"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateItem() throws Exception{
        mockMvc.perform(put("/api/v1/item/{itemId}", 1L)
                        .contentType("application/json")
                        .param("itemName", "Cake")
                        .param("itemPrice", "0.49"))
                .andExpect(status().isOk());
        verify(itemServiceTest, times(1)).updateItem(anyLong(), anyString(), anyDouble());
    }
}
