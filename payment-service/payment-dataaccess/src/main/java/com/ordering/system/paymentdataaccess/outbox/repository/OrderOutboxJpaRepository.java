package com.ordering.system.paymentdataaccess.outbox.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ordering.system.commondomain.valueobject.PaymentStatus;
import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.paymentdataaccess.outbox.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOutboxJpaRepository extends JpaRepository<OrderOutboxEntity, UUID> {

    Optional<List<OrderOutboxEntity>> findByTypeAndOutboxStatus(String type, OutboxStatus outboxStatus);

    Optional<OrderOutboxEntity> findByTypeAndSagaIdAndPaymentStatusAndOutboxStatus(String type,
                                                                    UUID sagaId,
                                                                    PaymentStatus paymentStatus,
                                                                    OutboxStatus outboxStatus);

    void deleteByTypeAndOutboxStatus(String type, OutboxStatus outboxStatus);

}
