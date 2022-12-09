package com.ordering.system.orderapplicationservice.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.ordering.system.orderdomaincore.entity.Customer;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);

    Customer save(Customer customer);
}
