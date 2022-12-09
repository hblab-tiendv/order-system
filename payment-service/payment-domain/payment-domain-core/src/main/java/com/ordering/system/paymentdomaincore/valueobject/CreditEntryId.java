package com.ordering.system.paymentdomaincore.valueobject;

import java.util.UUID;

import com.ordering.system.commondomain.valueobject.BaseId;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
