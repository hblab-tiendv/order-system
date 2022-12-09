package com.ordering.system.orderdomaincore.event;

import java.time.ZonedDateTime;

import com.ordering.system.orderdomaincore.entity.Order;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order,
                               ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
