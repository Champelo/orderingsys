package com.bakery.orderingsys.orderticket;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderTicketService {

    private final OrderTicketRepository orderTicketRepository;

    public OrderTicketService(OrderTicketRepository orderTicketRepository) {
        this.orderTicketRepository = orderTicketRepository;
    }

    public List<OrderTicket> getAllOrders() {
        return orderTicketRepository.findAll();
    }

    public OrderTicket getOrder(Long orderNo) {
        Optional<OrderTicket> orderOptional = orderTicketRepository.findById(orderNo);
        if (!orderOptional.isPresent()) {
            throw new IllegalStateException("Order doesn't exist");
        }

        OrderTicket orderTicket = orderTicketRepository.getById(orderNo);
        return orderTicket;
    }

    public void addNewOrder(OrderTicket orderTicket) {
        orderTicketRepository.save(orderTicket);
    }

    public void deleteOrder(Long orderNo) {
        boolean exist = orderTicketRepository.existsById(orderNo);

        if(!exist) {
            throw new IllegalStateException("Order doesn't exist");
        }
        orderTicketRepository.deleteById(orderNo);
    }

    //Update Order will require access to the items table
}
