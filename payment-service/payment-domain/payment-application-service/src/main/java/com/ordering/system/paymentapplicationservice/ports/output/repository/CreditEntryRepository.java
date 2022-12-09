package com.ordering.system.paymentapplicationservice.ports.output.repository;

import java.util.Optional;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.paymentdomaincore.entity.CreditEntry;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
