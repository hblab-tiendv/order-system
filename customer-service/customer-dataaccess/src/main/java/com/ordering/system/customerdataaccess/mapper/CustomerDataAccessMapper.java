package com.ordering.system.customerdataaccess.mapper;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.customerdataaccess.entity.CustomerEntity;
import com.ordering.system.customerdomaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity entity) {
        return new Customer(new CustomerId(entity.getId()),
                entity.getUsername(),
                entity.getFirstName(),
                entity.getLastName());
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}
