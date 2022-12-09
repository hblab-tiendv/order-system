package com.ordering.system.paymentdataaccess.payment.adapter;

import java.util.Optional;
import java.util.UUID;

import com.ordering.system.paymentapplicationservice.ports.output.repository.PaymentRepository;
import com.ordering.system.paymentdataaccess.payment.mapper.PaymentDataAccessMapper;
import com.ordering.system.paymentdataaccess.payment.repository.PaymentJpaRepository;
import com.ordering.system.paymentdomaincore.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentDataAccessMapper paymentDataAccessMapper;

    public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository,
                                 PaymentDataAccessMapper paymentDataAccessMapper) {
        this.paymentJpaRepository = paymentJpaRepository;
        this.paymentDataAccessMapper = paymentDataAccessMapper;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentDataAccessMapper
                .paymentEntityToPayment(paymentJpaRepository
                        .save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
    }

    @Override
    public Optional<Payment> findByOrderId(UUID orderId) {
        return paymentJpaRepository.findByOrderId(orderId)
                .map(paymentDataAccessMapper::paymentEntityToPayment);
    }
}
