package com.ordering.system.customerdomaincore.event;

import java.time.ZonedDateTime;

import com.ordering.system.commondomain.event.DomainEvent;
import com.ordering.system.customerdomaincore.entity.Customer;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;
    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}
