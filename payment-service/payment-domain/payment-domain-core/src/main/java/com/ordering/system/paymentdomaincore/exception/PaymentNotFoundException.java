package com.ordering.system.paymentdomaincore.exception;

import com.ordering.system.commondomain.exception.DomainException;

public class PaymentNotFoundException extends DomainException {

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
