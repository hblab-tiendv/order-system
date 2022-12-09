package com.ordering.system.customerdomaincore;

import com.ordering.system.customerdomaincore.entity.Customer;
import com.ordering.system.customerdomaincore.event.CustomerCreatedEvent;

public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);
}
