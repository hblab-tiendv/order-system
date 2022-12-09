package com.ordering.system.paymentapplicationservice.ports.output.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ordering.system.commondomain.valueobject.PaymentStatus;
import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.paymentapplicationservice.outbox.model.OrderOutboxMessage;

public interface OrderOutboxRepository {
    OrderOutboxMessage save(OrderOutboxMessage orderOutboxMessage);

    Optional<List<OrderOutboxMessage>> findByTypeAndOutboxStatus(String type, OutboxStatus status);

    Optional<OrderOutboxMessage> findByTypeAndSagaIdAndPaymentStatusAndOutboxStatus(String type,
                                                                                    UUID sagaId,
                                                                                    PaymentStatus paymentStatus,
                                                                                    OutboxStatus outboxStatus);
    void deleteByTypeAndOutboxStatus(String type, OutboxStatus status);
}
