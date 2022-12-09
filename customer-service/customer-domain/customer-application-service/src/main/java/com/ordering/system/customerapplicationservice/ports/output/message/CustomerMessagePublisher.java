package com.ordering.system.customerapplicationservice.ports.output.message;

import com.ordering.system.customerdomaincore.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {

    void publish(CustomerCreatedEvent customerCreatedEvent);

}