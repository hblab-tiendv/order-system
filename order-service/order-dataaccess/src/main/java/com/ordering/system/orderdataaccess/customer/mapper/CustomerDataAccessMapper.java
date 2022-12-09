package com.ordering.system.orderdataaccess.customer.mapper;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.orderdataaccess.customer.entity.CustomerEntity;
import com.ordering.system.orderdomaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
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
