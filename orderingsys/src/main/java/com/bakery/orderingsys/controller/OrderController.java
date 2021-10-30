package com.bakery.orderingsys.controller;

import com.bakery.orderingsys.model.OrderTicket;
import com.bakery.orderingsys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    List<OrderTicket> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "{orderNo}")
    OrderTicket getOrder(@PathVariable("orderNo") Long orderNo) {
        return orderService.getOrder(orderNo);
    }

    @PostMapping
    void addNewOrder(@RequestBody OrderTicket orderTicket) {
        orderService.addNewOrder(orderTicket);
    }

    @DeleteMapping(path = "{orderNo}")
    void deleteOrder(@PathVariable("orderNo") Long orderNo) {
        orderService.deleteOrder(orderNo);
    }

}
