package com.ordering.system.orderapplicationservice.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.commondomain.valueobject.Money;
import com.ordering.system.commondomain.valueobject.PaymentOrderStatus;
import com.ordering.system.commondomain.valueobject.ProductId;
import com.ordering.system.commondomain.valueobject.RestaurantId;
import com.ordering.system.commondomain.valueobject.RestaurantOrderStatus;
import com.ordering.system.orderapplicationservice.dto.create.CreateOrderCommand;
import com.ordering.system.orderapplicationservice.dto.create.CreateOrderResponse;
import com.ordering.system.orderapplicationservice.dto.create.OrderAddress;
import com.ordering.system.orderapplicationservice.dto.message.CustomerModel;
import com.ordering.system.orderapplicationservice.dto.track.TrackOrderResponse;
import com.ordering.system.orderapplicationservice.outbox.model.approval.OrderApprovalEventPayload;
import com.ordering.system.orderapplicationservice.outbox.model.approval.OrderApprovalEventProduct;
import com.ordering.system.orderapplicationservice.outbox.model.payment.OrderPaymentEventPayload;
import com.ordering.system.orderdomaincore.entity.Customer;
import com.ordering.system.orderdomaincore.entity.Order;
import com.ordering.system.orderdomaincore.entity.OrderItem;
import com.ordering.system.orderdomaincore.entity.Product;
import com.ordering.system.orderdomaincore.entity.Restaurant;
import com.ordering.system.orderdomaincore.event.OrderCreatedEvent;
import com.ordering.system.orderdomaincore.event.OrderPaidEvent;
import com.ordering.system.orderdomaincore.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

    public Customer customerModelToCustomer(CustomerModel customerModel) {
        return new Customer(new CustomerId(UUID.fromString(customerModel.getId())),
                customerModel.getUsername(),
                customerModel.getFirstName(),
                customerModel.getLastName());
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public OrderPaymentEventPayload orderCreatedEventToOrderPaymentEventPayload(OrderCreatedEvent orderCreatedEvent) {
        return OrderPaymentEventPayload.builder()
                .customerId(orderCreatedEvent.getOrder().getCustomerId().getValue().toString())
                .orderId(orderCreatedEvent.getOrder().getId().getValue().toString())
                .price(orderCreatedEvent.getOrder().getPrice().getAmount())
                .createdAt(orderCreatedEvent.getCreatedAt())
                .paymentOrderStatus(PaymentOrderStatus.PENDING.name())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                                new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    public OrderApprovalEventPayload orderPaidEventToOrderApprovalEventPayload(OrderPaidEvent orderPaidEvent) {
        return OrderApprovalEventPayload.builder()
                .orderId(orderPaidEvent.getOrder().getId().getValue().toString())
                .restaurantId(orderPaidEvent.getOrder().getRestaurantId().getValue().toString())
                .restaurantOrderStatus(RestaurantOrderStatus.PAID.name())
                .products(orderPaidEvent.getOrder().getItems().stream().map(orderItem ->
                        OrderApprovalEventProduct.builder()
                                .id(orderItem.getProduct().getId().getValue().toString())
                                .quantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .price(orderPaidEvent.getOrder().getPrice().getAmount())
                .createdAt(orderPaidEvent.getCreatedAt())
                .build();
    }



    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<com.ordering.system.orderapplicationservice.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .product(new Product(new ProductId(orderItem.getProductId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

}
