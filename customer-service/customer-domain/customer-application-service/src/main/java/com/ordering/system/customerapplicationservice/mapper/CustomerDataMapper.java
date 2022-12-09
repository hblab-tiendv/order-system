package com.ordering.system.customerapplicationservice.mapper;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.customerapplicationservice.create.CreateCustomerCommand;
import com.ordering.system.customerapplicationservice.create.CreateCustomerResponse;
import com.ordering.system.customerdomaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {

    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return new Customer(new CustomerId(createCustomerCommand.getCustomerId()),
                createCustomerCommand.getUsername(),
                createCustomerCommand.getFirstName(),
                createCustomerCommand.getLastName());
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String message) {
        return new CreateCustomerResponse(customer.getId().getValue(), message);
    }
}
