package com.bakery.orderingsys.orderticket;

import com.bakery.orderingsys.orderticket.OrderTicket;
import com.bakery.orderingsys.orderticket.OrderTicketService;
import com.bakery.orderingsys.orderticket.OrderTicketRepository;
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
    private OrderTicketRepository orderTicketRepositoryTest;
    private OrderTicketService orderTicketServiceTest;

    @BeforeEach
    void setUp() {
        orderTicketServiceTest = new OrderTicketService(orderTicketRepositoryTest);
    }

    @Test
    void getAllOrders() {
        orderTicketServiceTest.getAllOrders();
        verify(orderTicketRepositoryTest).findAll();
    }

    @Test
    void getOrder() {
        OrderTicket orderTicket = new OrderTicket();
        given(orderTicketRepositoryTest.findById(anyLong())).willReturn(Optional.of(orderTicket));
        orderTicketServiceTest.getOrder(anyLong());
        verify(orderTicketRepositoryTest).findById(anyLong());
        verify(orderTicketRepositoryTest).getById(anyLong());
    }

    @Test
    void getMissingOrder() {
        assertThatThrownBy(() -> orderTicketServiceTest.getOrder(anyLong()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Order doesn't exist");
    }

    @Test
    void addNewOrder() {
        OrderTicket orderTicket =new OrderTicket();
        orderTicketServiceTest.addNewOrder(orderTicket);
        ArgumentCaptor<OrderTicket> orderArgumentCaptor = ArgumentCaptor.forClass(OrderTicket.class);
        verify(orderTicketRepositoryTest).save(orderArgumentCaptor.capture());
        OrderTicket capturedOrderTicket = orderArgumentCaptor.getValue();

        assertThat(capturedOrderTicket).isEqualTo(orderTicket);

    }

    @Test
    void deleteOrder() {
        given(orderTicketRepositoryTest.existsById(anyLong())).willReturn(true);
        orderTicketServiceTest.deleteOrder(anyLong());
        verify(orderTicketRepositoryTest).deleteById(anyLong());
    }

    @Test
    void deleteMissingOrder() {
        given(orderTicketRepositoryTest.existsById(anyLong())).willReturn(false);
        assertThatThrownBy(() -> orderTicketServiceTest.deleteOrder(anyLong()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Order doesn't exist");
        verify(orderTicketRepositoryTest, never()).deleteById(anyLong());
    }
}