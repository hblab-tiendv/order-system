package com.ordering.system.commondomain.event.publisher;

import com.ordering.system.commondomain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
