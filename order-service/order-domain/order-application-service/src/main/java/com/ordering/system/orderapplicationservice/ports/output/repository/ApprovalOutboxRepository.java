package com.ordering.system.orderapplicationservice.ports.output.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ordering.system.orderapplicationservice.outbox.model.approval.OrderApprovalOutboxMessage;
import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.saga.SagaStatus;

public interface ApprovalOutboxRepository {

    OrderApprovalOutboxMessage save(OrderApprovalOutboxMessage orderApprovalOutboxMessage);

    Optional<List<OrderApprovalOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String type,
                                                                                     OutboxStatus outboxStatus,
                                                                                     SagaStatus... sagaStatus);
    Optional<OrderApprovalOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String type,
                                                                         UUID sagaId,
                                                                         SagaStatus... sagaStatus);
    void deleteByTypeAndOutboxStatusAndSagaStatus(String type,
                                                  OutboxStatus outboxStatus,
                                                  SagaStatus... sagaStatus);
}
