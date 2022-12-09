package com.ordering.system.paymentdomaincore.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import com.ordering.system.paymentdomaincore.entity.Payment;

public class PaymentCancelledEvent extends PaymentEvent {

    public PaymentCancelledEvent(Payment payment,
                                 ZonedDateTime createdAt) {
        super(payment, createdAt, Collections.emptyList());
    }

}
