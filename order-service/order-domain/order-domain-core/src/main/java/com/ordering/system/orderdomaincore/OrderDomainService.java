package com.ordering.system.orderdomaincore;

import java.util.List;

import com.ordering.system.orderdomaincore.entity.Order;
import com.ordering.system.orderdomaincore.entity.Restaurant;
import com.ordering.system.orderdomaincore.event.OrderCancelledEvent;
import com.ordering.system.orderdomaincore.event.OrderCreatedEvent;
import com.ordering.system.orderdomaincore.event.OrderPaidEvent;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
