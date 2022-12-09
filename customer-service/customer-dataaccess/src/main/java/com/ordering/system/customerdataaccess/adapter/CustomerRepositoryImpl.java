package com.ordering.system.customerdataaccess.adapter;

import com.ordering.system.customerapplicationservice.ports.output.repository.CustomerRepository;
import com.ordering.system.customerdataaccess.entity.CustomerEntity;
import com.ordering.system.customerdataaccess.mapper.CustomerDataAccessMapper;
import com.ordering.system.customerdataaccess.repository.CustomerJpaRepository;
import com.ordering.system.customerdomaincore.entity.Customer;
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
    public Customer createCustomer(Customer customer) {
        CustomerEntity entity = customerDataAccessMapper.customerToCustomerEntity(customer);
        CustomerEntity savedCustomerEntity = customerJpaRepository.save(entity);
        return customerDataAccessMapper.customerEntityToCustomer(savedCustomerEntity);
    }
}
