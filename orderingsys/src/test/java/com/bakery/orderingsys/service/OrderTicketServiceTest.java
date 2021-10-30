package com.bakery.orderingsys.service;

import com.bakery.orderingsys.model.OrderTicket;
import com.bakery.orderingsys.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderTicketServiceTest {

    @Mock
    private OrderRepository orderRepositoryTest;
    private OrderService orderServiceTest;

    @BeforeEach
    void setUp() {
        orderServiceTest = new OrderService(orderRepositoryTest);
    }

    @Test
    void getAllOrders() {
        orderServiceTest.getAllOrders();
        verify(orderRepositoryTest).findAll();
    }

    @Test
    void getOrder() {
        OrderTicket orderTicket = new OrderTicket();
        given(orderRepositoryTest.findById(anyLong())).willReturn(Optional.of(orderTicket));
        orderServiceTest.getOrder(anyLong());
        verify(orderRepositoryTest).findById(anyLong());
        verify(orderRepositoryTest).getById(anyLong());
    }

    @Test
    void getMissingOrder() {
        assertThatThrownBy(() -> orderServiceTest.getOrder(anyLong()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Order doesn't exist");
    }

    @Test
    void addNewOrder() {
        OrderTicket orderTicket =new OrderTicket();
        orderServiceTest.addNewOrder(orderTicket);
        ArgumentCaptor<OrderTicket> orderArgumentCaptor = ArgumentCaptor.forClass(OrderTicket.class);
        verify(orderRepositoryTest).save(orderArgumentCaptor.capture());
        OrderTicket capturedOrderTicket = orderArgumentCaptor.getValue();

        assertThat(capturedOrderTicket).isEqualTo(orderTicket);

    }

    @Test
    void deleteOrder() {
        given(orderRepositoryTest.existsById(anyLong())).willReturn(true);
        orderServiceTest.deleteOrder(anyLong());
        verify(orderRepositoryTest).deleteById(anyLong());
    }

    @Test
    void deleteMissingOrder() {
        given(orderRepositoryTest.existsById(anyLong())).willReturn(false);
        assertThatThrownBy(() -> orderServiceTest.deleteOrder(anyLong()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Order doesn't exist");
        verify(orderRepositoryTest, never()).deleteById(anyLong());
    }
}