package com.ordering.system.customerdomaincore.exception;

import com.ordering.system.commondomain.exception.DomainException;

public class CustomerDomainException extends DomainException {

    public CustomerDomainException(String message) {
        super(message);
    }
}
