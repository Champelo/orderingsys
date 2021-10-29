package com.bakery.orderingsys.service;

import com.bakery.orderingsys.model.Order;
import com.bakery.orderingsys.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long orderNo) {
        Optional<Order> orderOptional = orderRepository.findById(orderNo);
        if (!orderOptional.isPresent()) {
            throw new IllegalStateException("Order doesn't exist");
        }

        Order order = orderRepository.getById(orderNo);
        return order;
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
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
