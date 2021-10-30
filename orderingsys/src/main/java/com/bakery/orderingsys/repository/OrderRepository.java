package com.bakery.orderingsys.repository;

import com.bakery.orderingsys.model.OrderTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderTicket, Long> {
}
