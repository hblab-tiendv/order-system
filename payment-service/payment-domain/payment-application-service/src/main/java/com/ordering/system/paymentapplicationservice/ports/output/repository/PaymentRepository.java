package com.ordering.system.paymentapplicationservice.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.ordering.system.paymentdomaincore.entity.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findByOrderId(UUID orderId);
}
