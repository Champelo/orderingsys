package com.bakery.orderingsys.service;

import com.bakery.orderingsys.model.OrderTicket;
import com.bakery.orderingsys.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderTicket> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderTicket getOrder(Long orderNo) {
        Optional<OrderTicket> orderOptional = orderRepository.findById(orderNo);
        if (!orderOptional.isPresent()) {
            throw new IllegalStateException("Order doesn't exist");
        }

        OrderTicket orderTicket = orderRepository.getById(orderNo);
        return orderTicket;
    }

    public void addNewOrder(OrderTicket orderTicket) {
        orderRepository.save(orderTicket);
    }

    public void deleteOrder(Long orderNo) {
        boolean exist = orderRepository.existsById(orderNo);

        if(!exist) {
            throw new IllegalStateException("Order doesn't exist");
        }
        orderRepository.deleteById(orderNo);
    }

    //Update Order will require access to the items table
}
