package com.ordering.system.paymentdataaccess.outbox.entity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.ordering.system.commondomain.valueobject.PaymentStatus;
import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.paymentapplicationservice.outbox.model.OrderEventPayload;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
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
@Table(name = "order_outbox")
@Entity
public class OrderOutboxEntity {

    @Id
    private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private String type;
    @Type(JsonBinaryType.class)
    private OrderEventPayload payload;
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private OutboxStatus outboxStatus;
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private PaymentStatus paymentStatus;
    @Version
    private int version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderOutboxEntity that = (OrderOutboxEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

