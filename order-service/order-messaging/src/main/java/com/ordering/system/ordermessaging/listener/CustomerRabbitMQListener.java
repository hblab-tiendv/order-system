package com.ordering.system.ordermessaging.listener;

import com.ordering.system.messageconsumer.RabbitMQConsumer;
import com.ordering.system.messagemodel.CustomerAvroModel;
import com.ordering.system.orderapplicationservice.ports.input.message.listener.customer.CustomerMessageListener;
import com.ordering.system.ordermessaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerRabbitMQListener implements RabbitMQConsumer<CustomerAvroModel> {

    private final CustomerMessageListener customerMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    public CustomerRabbitMQListener(CustomerMessageListener customerMessageListener,
                                    OrderMessagingDataMapper orderMessagingDataMapper) {
        this.customerMessageListener = customerMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    @RabbitListener(queues = "Customer")
    public void receive(CustomerAvroModel customerAvroModel) {
        customerMessageListener.customerCreated(orderMessagingDataMapper
                .customerAvroModeltoCustomerModel(customerAvroModel));
    }
}
