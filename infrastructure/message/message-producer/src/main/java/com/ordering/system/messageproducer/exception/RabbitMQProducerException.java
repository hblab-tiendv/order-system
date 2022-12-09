package com.ordering.system.messageproducer.exception;

public class RabbitMQProducerException extends RuntimeException {

    public RabbitMQProducerException(String message) {
        super(message);
    }
}
