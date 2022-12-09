package com.ordering.system.orderapplicationservice.ports.input.message.listener.customer;

import com.ordering.system.orderapplicationservice.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
