package com.ordering.system.messageproducer.service;

public interface RabbitMQProducer<T> {
    void send(String exchange, String routingKey, T message);
}
