package com.ordering.system.customerdomaincore;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.ordering.system.commondomain.DomainConstants;
import com.ordering.system.customerdomaincore.entity.Customer;
import com.ordering.system.customerdomaincore.event.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerDomainServiceImpl implements CustomerDomainService {
    @Override
    public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
        log.info("Customer with id: {} is initiated", customer.getId().getValue());
        return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)));
    }
}
