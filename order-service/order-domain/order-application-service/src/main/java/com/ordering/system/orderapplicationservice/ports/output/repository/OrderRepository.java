package com.ordering.system.orderapplicationservice.ports.output.repository;

import java.util.Optional;

import com.ordering.system.commondomain.valueobject.OrderId;
import com.ordering.system.orderdomaincore.entity.Order;
import com.ordering.system.orderdomaincore.valueobject.TrackingId;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
