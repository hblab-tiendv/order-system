package com.ordering.system.paymentapplicationservice.ports.output.message.publisher;

import java.util.function.BiConsumer;

import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.paymentapplicationservice.outbox.model.OrderOutboxMessage;

public interface PaymentResponseMessagePublisher {
    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
