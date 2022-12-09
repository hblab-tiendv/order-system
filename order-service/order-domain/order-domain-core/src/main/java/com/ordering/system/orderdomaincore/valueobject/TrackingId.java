package com.ordering.system.orderdomaincore.valueobject;

import java.util.UUID;

import com.ordering.system.commondomain.valueobject.BaseId;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
