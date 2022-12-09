package com.ordering.system.messageproducer.service.impl;

import com.ordering.system.messageproducer.exception.RabbitMQProducerException;
import com.ordering.system.messageproducer.service.RabbitMQProducer;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQProducerImpl<T> implements RabbitMQProducer<T> {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(String exchange, String routingKey, T message) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (Exception e) {
            log.error("Error on rabbitMQ producer with key: {}, message: {} and exception: {}", routingKey, message,
                    e.getMessage());
            throw new RabbitMQProducerException("Error on rabbitMQ producer with key: " + routingKey + " and message: " + message);
        }
    }

    @PreDestroy
    public void close() {
        if (rabbitTemplate != null) {
            log.info("Closing rabbitMQ producer!");
            rabbitTemplate.destroy();
        }
    }
}
