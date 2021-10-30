package com.bakery.orderingsys.orderticket;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderTicketController {

    private final OrderTicketService orderTicketService;

    public OrderTicketController(OrderTicketService orderTicketService) {
        this.orderTicketService = orderTicketService;
    }

    @GetMapping
    List<OrderTicket> getAllOrders() {
        return orderTicketService.getAllOrders();
    }

    @GetMapping(path = "/{orderNo}")
    @ResponseBody
    OrderTicket getOrder(@PathVariable("orderNo") Long orderNo) {
        return orderTicketService.getOrder(orderNo);
    }

    @PostMapping
    void addNewOrder(@RequestBody OrderTicket orderTicket) {
        orderTicketService.addNewOrder(orderTicket);
    }

    @DeleteMapping(path = "/{orderNo}")
    void deleteOrder(@PathVariable("orderNo") Long orderNo) {
        orderTicketService.deleteOrder(orderNo);
    }

}
