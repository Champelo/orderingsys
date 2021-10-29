package com.bakery.orderingsys.controller;

import com.bakery.orderingsys.model.Order;
import com.bakery.orderingsys.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "{orderNo}")
    Order getOrder(@PathVariable("orderNo") Long orderNo) {
        return orderService.getOrder(orderNo);
    }

    @PostMapping
    void addNewOrder(@RequestBody Order order) {
        orderService.addNewOrder(order);
    }

    @DeleteMapping(path = "{orderNo}")
    void deleteOrder(@PathVariable("orderNo") Long orderNo) {
        orderService.deleteOrder(orderNo);
    }

}
