package com.ordering.system.paymentapplicationservice.ports.output.repository;

import java.util.List;
import java.util.Optional;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.paymentdomaincore.entity.CreditHistory;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
