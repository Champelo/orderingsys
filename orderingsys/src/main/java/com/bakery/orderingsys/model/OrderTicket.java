package com.bakery.orderingsys.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "OrderTicket")
@Table(name = "order_ticket")
public class OrderTicket {
    @Id
    @SequenceGenerator(name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "order_sequence")
    @Column(name = "order_no", updatable = false)
    private Long orderNo;


    @Column(name = "ordered_TimeStamp",
            updatable = false)
    @CreationTimestamp
    private Date orderedTimeStamp;

    @ManyToMany
    @JoinTable(
            name = "orderedItems",
            joinColumns = @JoinColumn(name = "order_no"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    Set<Item> orderedItems;

    public OrderTicket() {
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
