package com.bakery.orderingsys.orderticket;

import com.bakery.orderingsys.orderticket.OrderTicket;
import com.bakery.orderingsys.orderticket.OrderTicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class OrderTicketConfig {

    @Bean
    CommandLineRunner orderTicketCommandLineRunner (OrderTicketRepository orderTicketRepository){
        return args -> {
            OrderTicket orderTicket1 = new OrderTicket();
            OrderTicket orderTicket2 = new OrderTicket();

            orderTicketRepository.saveAll(
                    List.of(orderTicket1, orderTicket2)
            );

        };
    }
}
