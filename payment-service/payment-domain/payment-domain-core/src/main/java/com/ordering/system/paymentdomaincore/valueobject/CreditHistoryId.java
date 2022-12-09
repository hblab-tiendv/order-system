package com.ordering.system.paymentdomaincore.valueobject;

import java.util.UUID;

import com.ordering.system.commondomain.valueobject.BaseId;

public class CreditHistoryId extends BaseId<UUID> {
    public CreditHistoryId(UUID value) {
        super(value);
    }
}
