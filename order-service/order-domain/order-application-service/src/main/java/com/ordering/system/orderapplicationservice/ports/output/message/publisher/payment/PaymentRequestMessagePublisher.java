package com.ordering.system.orderapplicationservice.ports.output.message.publisher.payment;

import java.util.function.BiConsumer;

import com.ordering.system.orderapplicationservice.outbox.model.payment.OrderPaymentOutboxMessage;
import com.ordering.system.outbox.OutboxStatus;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
