package com.bakery.orderingsys.controller;

import com.bakery.orderingsys.model.Order;
import com.bakery.orderingsys.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderServiceTest;

    private List<Order> orderList;

    @BeforeEach
    void setUp() {
        this.orderList = new ArrayList<>();
        this.orderList.add(new Order());
        this.orderList.add(new Order());
        this.orderList.add(new Order());
    }

    @Test
    void shouldGetAllOrders() throws Exception{
       //when(orderServiceTest.getAllOrders()).thenReturn(orderList);
    }

    @Test
    void getOrder() {
    }

    @Test
    void addNewOrder() {
    }

    @Test
    void deleteOrder() {
    }
}