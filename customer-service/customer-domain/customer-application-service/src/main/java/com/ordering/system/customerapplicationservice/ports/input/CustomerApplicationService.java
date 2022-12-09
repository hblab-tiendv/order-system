package com.ordering.system.customerapplicationservice.ports.input;

import com.ordering.system.customerapplicationservice.create.CreateCustomerCommand;
import com.ordering.system.customerapplicationservice.create.CreateCustomerResponse;
import jakarta.validation.Valid;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

}
