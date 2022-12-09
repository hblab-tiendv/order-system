package com.ordering.system.paymentapplicationservice.mapper;

import java.util.UUID;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.commondomain.valueobject.Money;
import com.ordering.system.commondomain.valueobject.OrderId;
import com.ordering.system.paymentapplicationservice.dto.PaymentRequest;
import com.ordering.system.paymentapplicationservice.outbox.model.OrderEventPayload;
import com.ordering.system.paymentdomaincore.entity.Payment;
import com.ordering.system.paymentdomaincore.event.PaymentEvent;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }

    public OrderEventPayload paymentEventToOrderEventPayload(PaymentEvent paymentEvent) {
        return OrderEventPayload.builder()
                .paymentId(paymentEvent.getPayment().getId().getValue().toString())
                .customerId(paymentEvent.getPayment().getCustomerId().getValue().toString())
                .orderId(paymentEvent.getPayment().getOrderId().getValue().toString())
                .price(paymentEvent.getPayment().getPrice().getAmount())
                .createdAt(paymentEvent.getCreatedAt())
                .paymentStatus(paymentEvent.getPayment().getPaymentStatus().name())
                .failureMessages(paymentEvent.getFailureMessages())
                .build();
    }
}
