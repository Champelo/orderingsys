package com.bakery.orderingsys.controller;

import com.bakery.orderingsys.model.OrderTicket;
import com.bakery.orderingsys.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = OrderController.class)
class OrderTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderServiceTest;

    private List<OrderTicket> orderTicketList;

    @BeforeEach
    void setUp() {
        this.orderTicketList = new ArrayList<>();
        this.orderTicketList.add(new OrderTicket());
        this.orderTicketList.add(new OrderTicket());
        this.orderTicketList.add(new OrderTicket());
    }

    @Test
    void shouldGetAllOrders() throws Exception {
       given(orderServiceTest.getAllOrders()).willReturn(orderTicketList);

       this.mockMvc.perform(get("/api/v1/order"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.[*]").exists())
               .andExpect(jsonPath("$[*].orderNo").isNotEmpty());
    }

    @Test
    void getOrder() throws Exception {
        given(orderServiceTest.getOrder(orderTicketList.get(0).getOrderNo())).willReturn(orderTicketList.get(0));

        this.mockMvc.perform(get("/api/v1/order/{orderNo}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].orderNo").value(0));

    }

    @Test
    void addNewOrder() {
    }

    @Test
    void deleteOrder() {
    }
}