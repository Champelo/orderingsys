package com.bakery.orderingsys.orderticket;

import com.bakery.orderingsys.orderticket.OrderTicket;
import com.bakery.orderingsys.orderticket.OrderTicketController;
import com.bakery.orderingsys.orderticket.OrderTicketService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = OrderTicketController.class)
class OrderTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderTicketService orderTicketServiceTest;

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

       given(orderTicketServiceTest.getAllOrders()).willReturn(orderTicketList);

       this.mockMvc.perform(get("/api/v1/order"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.[*]").exists())
               .andExpect(jsonPath("$[*].orderNo").isNotEmpty())
               .andExpect(jsonPath("$[*].orderedTimeStamp").isNotEmpty());
    }

    @Test
    void shouldGetOrder() throws Exception {
        final OrderTicket orderTicket = new OrderTicket();

        when(orderTicketServiceTest.getOrder(1L)).thenReturn(orderTicket);

        mockMvc.perform(get("/api/v1/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.orderNo").value(orderTicketList.get(0).getOrderNo()))
                .andExpect(jsonPath("$.orderedTimeStamp").value(orderTicketList.get(0).getOrderedTimeStamp()));
    }

    @Test
    void shouldAddNewOrder() throws Exception{

        mockMvc.perform(post("/api/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        verify(orderTicketServiceTest).addNewOrder(any(OrderTicket.class));
    }

    @Test
    void shouldDeleteOrder() throws Exception{
        mockMvc.perform(delete("/api/v1/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(orderTicketServiceTest).deleteOrder(anyLong());
    }
}