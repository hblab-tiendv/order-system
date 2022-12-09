package com.ordering.system.orderdomaincore.event;

import java.time.ZonedDateTime;

import com.ordering.system.commondomain.event.DomainEvent;
import com.ordering.system.orderdomaincore.entity.Order;

public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;

    public OrderEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
