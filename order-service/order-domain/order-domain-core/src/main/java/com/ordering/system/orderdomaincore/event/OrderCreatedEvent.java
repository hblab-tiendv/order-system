package com.ordering.system.orderdomaincore.event;

import java.time.ZonedDateTime;

import com.ordering.system.orderdomaincore.entity.Order;

public class OrderCreatedEvent extends OrderEvent {
    public OrderCreatedEvent(Order order,
                             ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
