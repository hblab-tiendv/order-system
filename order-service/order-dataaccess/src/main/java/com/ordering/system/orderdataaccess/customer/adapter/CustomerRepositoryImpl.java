package com.ordering.system.orderdataaccess.customer.adapter;

import java.util.Optional;
import java.util.UUID;

import com.ordering.system.orderapplicationservice.ports.output.repository.CustomerRepository;
import com.ordering.system.orderdataaccess.customer.mapper.CustomerDataAccessMapper;
import com.ordering.system.orderdataaccess.customer.repository.CustomerJpaRepository;
import com.ordering.system.orderdomaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerDataAccessMapper::customerEntityToCustomer);
    }

    @Override
    public Customer save(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
    }
}
