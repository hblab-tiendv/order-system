package com.ordering.system.orderdomaincore.valueobject;

import com.ordering.system.commondomain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
