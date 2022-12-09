package com.ordering.system.paymentdataaccess.payment.repository;

import java.util.Optional;
import java.util.UUID;

import com.ordering.system.paymentdataaccess.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findByOrderId(UUID orderId);


}
