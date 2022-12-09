package com.ordering.system.ordermessaging.publisher;

import java.util.function.BiConsumer;

import com.ordering.system.messagemodel.PaymentRequestAvroModel;
import com.ordering.system.messageproducer.service.RabbitMQProducer;
import com.ordering.system.orderapplicationservice.outbox.model.payment.OrderPaymentEventPayload;
import com.ordering.system.orderapplicationservice.outbox.model.payment.OrderPaymentOutboxMessage;
import com.ordering.system.orderapplicationservice.ports.output.message.publisher.payment.PaymentRequestMessagePublisher;
import com.ordering.system.ordermessaging.mapper.OrderMessagingDataMapper;
import com.ordering.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderPaymentEventRabbitMQPublisher implements PaymentRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final RabbitMQProducer<PaymentRequestAvroModel> rabbitMQProducer;

    public OrderPaymentEventRabbitMQPublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                              RabbitMQProducer<PaymentRequestAvroModel> rabbitMQProducer) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                        BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback) {
        OrderPaymentEventPayload orderPaymentEventPayload = orderPaymentOutboxMessage.getPayload();

        String sagaId = orderPaymentOutboxMessage.getSagaId().toString();

        log.info("Received OrderPaymentOutboxMessage for order id: {} and saga id: {}",
                orderPaymentEventPayload.getOrderId(),
                sagaId);
        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderPaymentEventToPaymentRequestAvroModel(sagaId, orderPaymentEventPayload);
            rabbitMQProducer.send("Direct-Exchange", "payment", paymentRequestAvroModel);
            log.info("OrderPaymentEventPayload sent to RabbitMQ for order id: {} and saga id: {}",
                    orderPaymentEventPayload.getOrderId(), sagaId);
        } catch (Exception e) {
           log.error("Error while sending OrderPaymentEventPayload" +
                           " to RabbitMQ with order id: {} and saga id: {}, error: {}",
                   orderPaymentEventPayload.getOrderId(), sagaId, e.getMessage());
        }


    }
}
