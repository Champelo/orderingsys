package com.bakery.orderingsys.config;

import com.bakery.orderingsys.repository.ItemRepository;
import com.bakery.orderingsys.model.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner commandLineRunner (ItemRepository itemRepository){
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


            itemRepository.saveAll(
                    List.of(marbled_brownies, apple_pie)
            );

        };
    }
}
