package com.ordering.system.orderapplicationservice.ports.output.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ordering.system.orderapplicationservice.outbox.model.payment.OrderPaymentOutboxMessage;
import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.saga.SagaStatus;

public interface PaymentOutboxRepository {

    OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage);

    Optional<List<OrderPaymentOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String type,
                                                                                     OutboxStatus outboxStatus,
                                                                                     SagaStatus... sagaStatus);
    Optional<OrderPaymentOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String type,
                                                                         UUID sagaId,
                                                                         SagaStatus... sagaStatus);
    void deleteByTypeAndOutboxStatusAndSagaStatus(String type,
                                                  OutboxStatus outboxStatus,
                                                  SagaStatus... sagaStatus);
}
