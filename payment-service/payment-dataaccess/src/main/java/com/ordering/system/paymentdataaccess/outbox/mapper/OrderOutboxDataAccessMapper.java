package com.ordering.system.paymentdataaccess.outbox.mapper;

import com.ordering.system.paymentapplicationservice.outbox.model.OrderOutboxMessage;
import com.ordering.system.paymentdataaccess.outbox.entity.OrderOutboxEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderOutboxDataAccessMapper {

    public OrderOutboxEntity orderOutboxMessageToOutboxEntity(OrderOutboxMessage orderOutboxMessage) {
        return OrderOutboxEntity.builder()
                .id(orderOutboxMessage.getId())
                .sagaId(orderOutboxMessage.getSagaId())
                .createdAt(orderOutboxMessage.getCreatedAt())
                .type(orderOutboxMessage.getType())
                .payload(orderOutboxMessage.getPayload())
                .outboxStatus(orderOutboxMessage.getOutboxStatus())
                .paymentStatus(orderOutboxMessage.getPaymentStatus())
                .version(orderOutboxMessage.getVersion())
                .build();
    }

    public OrderOutboxMessage orderOutboxEntityToOrderOutboxMessage(OrderOutboxEntity paymentOutboxEntity) {
        return OrderOutboxMessage.builder()
                .id(paymentOutboxEntity.getId())
                .sagaId(paymentOutboxEntity.getSagaId())
                .createdAt(paymentOutboxEntity.getCreatedAt())
                .type(paymentOutboxEntity.getType())
                .payload(paymentOutboxEntity.getPayload())
                .outboxStatus(paymentOutboxEntity.getOutboxStatus())
                .paymentStatus(paymentOutboxEntity.getPaymentStatus())
                .version(paymentOutboxEntity.getVersion())
                .build();
    }

}
