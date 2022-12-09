package com.ordering.system.orderdomaincore.event;

import java.time.ZonedDateTime;

import com.ordering.system.orderdomaincore.entity.Order;

public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order,
                          ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
