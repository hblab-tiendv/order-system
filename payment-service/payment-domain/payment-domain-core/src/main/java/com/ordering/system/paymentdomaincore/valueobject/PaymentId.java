package com.ordering.system.paymentdomaincore.valueobject;

import java.util.UUID;

import com.ordering.system.commondomain.valueobject.BaseId;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
