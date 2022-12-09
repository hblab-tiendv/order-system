package com.ordering.system.paymentmessaging.publisher;

import java.util.function.BiConsumer;

import com.ordering.system.messagemodel.PaymentResponseAvroModel;
import com.ordering.system.messageproducer.service.RabbitMQProducer;
import com.ordering.system.outbox.OutboxStatus;
import com.ordering.system.paymentapplicationservice.outbox.model.OrderEventPayload;
import com.ordering.system.paymentapplicationservice.outbox.model.OrderOutboxMessage;
import com.ordering.system.paymentapplicationservice.ports.output.message.publisher.PaymentResponseMessagePublisher;
import com.ordering.system.paymentmessaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentEventRabbitMQPublisher implements PaymentResponseMessagePublisher {

    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final RabbitMQProducer<PaymentResponseAvroModel> rabbitMQProducer;

    public PaymentEventRabbitMQPublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
                                         RabbitMQProducer<PaymentResponseAvroModel> rabbitMQProducer) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void publish(OrderOutboxMessage orderOutboxMessage,
                        BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback) {
        OrderEventPayload orderEventPayload = orderOutboxMessage.getPayload();

        String sagaId = orderOutboxMessage.getSagaId().toString();

        log.info("Received OrderOutboxMessage for order id: {} and saga id: {}",
                orderEventPayload.getOrderId(),
                sagaId);

        try {
            PaymentResponseAvroModel paymentResponseAvroModel = paymentMessagingDataMapper
                    .orderEventPayloadToPaymentResponseAvroModel(sagaId, orderEventPayload);

            rabbitMQProducer.send("Direct-Exchange", "order-payment", paymentResponseAvroModel);

            log.info("PaymentResponseAvroModel sent to rabbitMQ for order id: {} and saga id: {}",
                    paymentResponseAvroModel.getOrderId(), sagaId);
        } catch (Exception e) {
            log.error("Error while sending PaymentRequestAvroModel message" +
                            " to rabbitMQ with order id: {} and saga id: {}, error: {}",
                    orderEventPayload.getOrderId(), sagaId, e.getMessage());
        }
    }
}
