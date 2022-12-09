package com.ordering.system.orderdataaccess.outbox.payment.entity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.ordering.system.commondomain.valueobject.OrderStatus;
import com.ordering.system.orderapplicationservice.outbox.model.payment.OrderPaymentEventPayload;
import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.saga.SagaStatus;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_outbox")
@Entity
public class PaymentOutboxEntity {

    @Id
    private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private String type;

    @Type(JsonBinaryType.class)
    private OrderPaymentEventPayload payload;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "saga_status")
    @Type(PostgreSQLEnumType.class)
    private SagaStatus sagaStatus;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    @Type(PostgreSQLEnumType.class)
    private OrderStatus orderStatus;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "outbox_status")
    @Type(PostgreSQLEnumType.class)
    private OutboxStatus outboxStatus;
    @Version
    private int version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentOutboxEntity that = (PaymentOutboxEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

