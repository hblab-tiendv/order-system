package com.ordering.system.ordermessaging.listener;

import com.ordering.system.messageconsumer.RabbitMQConsumer;
import com.ordering.system.messagemodel.PaymentResponseAvroModel;
import com.ordering.system.messagemodel.PaymentStatus;
import com.ordering.system.orderapplicationservice.ports.input.message.listener.payment.PaymentResponseMessageListener;
import com.ordering.system.orderdomaincore.exception.OrderNotFoundException;
import com.ordering.system.ordermessaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentRabbitMQListener implements RabbitMQConsumer<PaymentResponseAvroModel> {

    private final PaymentResponseMessageListener paymentResponseMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    public PaymentRabbitMQListener(PaymentResponseMessageListener paymentResponseMessageListener,
                                   OrderMessagingDataMapper orderMessagingDataMapper) {
        this.paymentResponseMessageListener = paymentResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    @RabbitListener(queues = "Order-Payment")
    public void receive(PaymentResponseAvroModel paymentResponseAvroModel) {
        try {
            if (PaymentStatus.COMPLETED == paymentResponseAvroModel.getPaymentStatus()) {
                log.info("Processing successful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                paymentResponseMessageListener.paymentCompleted(orderMessagingDataMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            } else if (PaymentStatus.CANCELLED == paymentResponseAvroModel.getPaymentStatus() ||
                    PaymentStatus.FAILED == paymentResponseAvroModel.getPaymentStatus()) {
                log.info("Processing unsuccessful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                paymentResponseMessageListener.paymentCancelled(orderMessagingDataMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            }
        } catch (OptimisticLockingFailureException e) {
            //NO-OP for optimistic lock. This means another thread finished the work, do not throw error to prevent reading the data from kafka again!
            log.error("Caught optimistic locking exception in PaymentResponseRabbitMQListener for order id: {}",
                    paymentResponseAvroModel.getOrderId());
        } catch (OrderNotFoundException e) {
            //NO-OP for OrderNotFoundException
            log.error("No order found for order id: {}", paymentResponseAvroModel.getOrderId());
        }
    }
}
