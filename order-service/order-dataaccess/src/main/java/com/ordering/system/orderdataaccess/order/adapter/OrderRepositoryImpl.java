package com.ordering.system.orderdataaccess.order.adapter;

import java.util.Optional;

import com.ordering.system.commondomain.valueobject.OrderId;
import com.ordering.system.orderapplicationservice.ports.output.repository.OrderRepository;
import com.ordering.system.orderdataaccess.order.entity.OrderEntity;
import com.ordering.system.orderdataaccess.order.mapper.OrderDataAccessMapper;
import com.ordering.system.orderdataaccess.order.repository.OrderJpaRepository;
import com.ordering.system.orderdomaincore.entity.Order;
import com.ordering.system.orderdomaincore.valueobject.TrackingId;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository,
                               OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order));
        return orderDataAccessMapper.orderEntityToOrder(entity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderJpaRepository.findById(orderId.getValue()).map(orderDataAccessMapper::orderEntityToOrder);
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
