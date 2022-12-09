package com.ordering.system.paymentmessaging.listener;

import java.sql.SQLException;

import com.ordering.system.messageconsumer.RabbitMQConsumer;
import com.ordering.system.messagemodel.PaymentOrderStatus;
import com.ordering.system.messagemodel.PaymentRequestAvroModel;
import com.ordering.system.paymentapplicationservice.exception.PaymentApplicationServiceException;
import com.ordering.system.paymentapplicationservice.ports.input.message.listener.PaymentRequestMessageListener;
import com.ordering.system.paymentdomaincore.exception.PaymentNotFoundException;
import com.ordering.system.paymentmessaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLState;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentRequestRabbitMQListener implements RabbitMQConsumer<PaymentRequestAvroModel> {

    private final PaymentRequestMessageListener paymentRequestMessageListener;
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;

    public PaymentRequestRabbitMQListener(PaymentRequestMessageListener paymentRequestMessageListener,
                                          PaymentMessagingDataMapper paymentMessagingDataMapper) {
        this.paymentRequestMessageListener = paymentRequestMessageListener;
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
    }

    @Override
    @RabbitListener(queues = "Payment")
    public void receive(PaymentRequestAvroModel paymentRequestAvroModel) {
        try {
            if (PaymentOrderStatus.PENDING == paymentRequestAvroModel.getPaymentOrderStatus()) {
                log.info("Processing payment for order id: {}", paymentRequestAvroModel.getOrderId());
                paymentRequestMessageListener.completePayment(paymentMessagingDataMapper
                        .paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
            } else if (PaymentOrderStatus.CANCELLED == paymentRequestAvroModel.getPaymentOrderStatus()) {
                log.info("Cancelling payment for order id: {}", paymentRequestAvroModel.getOrderId());
                paymentRequestMessageListener.cancelPayment(paymentMessagingDataMapper
                        .paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
            }
        } catch (DataAccessException e) {
            SQLException sqlException = (SQLException) e.getRootCause();
            if (sqlException != null && sqlException.getSQLState() != null &&
                    PSQLState.UNIQUE_VIOLATION.getState().equals(sqlException.getSQLState())) {
                //NO-OP for unique constraint exception
                log.error("Caught unique constraint exception with sql state: {} " +
                                "in PaymentRequestRabbitMQListener for order id: {}",
                        sqlException.getSQLState(), paymentRequestAvroModel.getOrderId());
            } else {
                throw new PaymentApplicationServiceException("Throwing DataAccessException in" +
                        " PaymentRequestRabbitMQListener: " + e.getMessage(), e);
            }
        } catch (PaymentNotFoundException e) {
            //NO-OP for PaymentNotFoundException
            log.error("No payment found for order id: {}", paymentRequestAvroModel.getOrderId());
        }
    }
}
