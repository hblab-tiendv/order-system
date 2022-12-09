package com.ordering.system.paymentdomaincore.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.ordering.system.paymentdomaincore.entity.Payment;

public class PaymentFailedEvent extends PaymentEvent {

    public PaymentFailedEvent(Payment payment,
                              ZonedDateTime createdAt,
                              List<String> failureMessages) {
        super(payment, createdAt, failureMessages);
    }

}
