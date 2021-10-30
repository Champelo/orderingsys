package com.bakery.orderingsys;

import com.bakery.orderingsys.item.Item;
import com.bakery.orderingsys.item.ItemRepository;
import com.bakery.orderingsys.orderticket.OrderTicket;
import com.bakery.orderingsys.orderticket.OrderTicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner (ItemRepository itemRepository, OrderTicketRepository orderTicketRepository){
        return args -> {
            Item marbled_brownies = new Item(
                    "Marbled Brownies",
                    5.99,
                    "Flour Sugar, Cocoa, Eggs",
                    false,
                    5
            );

            Item apple_pie = new Item(
                    "Apple Pie",
                    3.99,
                    "Flour, Sugar, Apples, Eggs",
                    true,
                    12
            );

            OrderTicket orderTicket1 = new OrderTicket();
            OrderTicket orderTicket2 = new OrderTicket();


            itemRepository.saveAll(
                    List.of(marbled_brownies, apple_pie)
            );

            orderTicketRepository.saveAll(
                    List.of(orderTicket1, orderTicket2)
            );

        };
    }
}
