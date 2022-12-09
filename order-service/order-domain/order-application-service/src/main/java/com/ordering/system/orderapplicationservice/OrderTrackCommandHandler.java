package com.ordering.system.orderapplicationservice;

import java.util.Optional;

import com.ordering.system.orderapplicationservice.dto.track.TrackOrderQuery;
import com.ordering.system.orderapplicationservice.dto.track.TrackOrderResponse;
import com.ordering.system.orderapplicationservice.mapper.OrderDataMapper;
import com.ordering.system.orderapplicationservice.ports.output.repository.OrderRepository;
import com.ordering.system.orderdomaincore.exception.OrderNotFoundException;
import com.ordering.system.orderdomaincore.valueobject.TrackingId;
import com.ordering.system.orderdomaincore.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
           Optional<Order> orderResult =
                   orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
           if (orderResult.isEmpty()) {
               log.warn("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
               throw new OrderNotFoundException("Could not find order with tracking id: " +
                       trackOrderQuery.getOrderTrackingId());
           }
           return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
