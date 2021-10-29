package com.bakery.orderingsys.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Order")
@Table(name = "order")
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "order_sequence")
    @Column(name = "order_No", updatable = false)
    private Long orderNo;


    @Column(name = "ordered_TimeStamp",
            updatable = false)
    @CreationTimestamp
    private Date orderedTimeStamp;

    public Order() {
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderedTimeStamp() {
        return orderedTimeStamp;
    }

    public void setOrderedTimeStamp(Date orderedTimeStamp) {
        this.orderedTimeStamp = orderedTimeStamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", orderedTimeStamp=" + orderedTimeStamp +
                '}';
    }
}
