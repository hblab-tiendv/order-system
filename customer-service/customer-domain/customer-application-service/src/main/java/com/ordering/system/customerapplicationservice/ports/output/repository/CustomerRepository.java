package com.ordering.system.customerapplicationservice.ports.output.repository;

import com.ordering.system.customerdomaincore.entity.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);
}
