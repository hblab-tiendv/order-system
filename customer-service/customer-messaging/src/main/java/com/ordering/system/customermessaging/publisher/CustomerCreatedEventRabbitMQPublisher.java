package com.ordering.system.customermessaging.publisher;

import com.ordering.system.customerapplicationservice.ports.output.message.CustomerMessagePublisher;
import com.ordering.system.customerdomaincore.event.CustomerCreatedEvent;
import com.ordering.system.customermessaging.mapper.CustomerMessagingDataMapper;
import com.ordering.system.messagemodel.CustomerAvroModel;
import com.ordering.system.messageproducer.service.RabbitMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerCreatedEventRabbitMQPublisher implements CustomerMessagePublisher {

    private final CustomerMessagingDataMapper customerMessagingDataMapper;

    private final RabbitMQProducer<CustomerAvroModel> rabbitMQProducer;

    public CustomerCreatedEventRabbitMQPublisher(CustomerMessagingDataMapper customerMessagingDataMapper,
                                                 RabbitMQProducer<CustomerAvroModel> rabbitMQProducer) {
        this.customerMessagingDataMapper = customerMessagingDataMapper;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void publish(CustomerCreatedEvent customerCreatedEvent) {
        log.info("Received CustomerCreatedEvent for customer id: {}",
                customerCreatedEvent.getCustomer().getId().getValue());
        try {
            CustomerAvroModel customerAvroModel = customerMessagingDataMapper
                    .paymentResponseAvroModelToPaymentResponse(customerCreatedEvent);

            rabbitMQProducer.send("Direct-Exchange", "customer", customerAvroModel);

            log.info("CustomerCreatedEvent sent to RabbitMQ for customer id: {}",
                    customerAvroModel.getId());
        } catch (Exception e) {
            log.error("Error while sending CustomerCreatedEvent to RabbitMQ for customer id: {}," +
                    " error: {}", customerCreatedEvent.getCustomer().getId().getValue(), e.getMessage());
        }
    }
}
