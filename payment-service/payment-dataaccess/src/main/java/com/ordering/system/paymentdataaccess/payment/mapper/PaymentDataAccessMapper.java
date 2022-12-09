package com.ordering.system.paymentdataaccess.payment.mapper;

import com.ordering.system.commondomain.valueobject.CustomerId;
import com.ordering.system.commondomain.valueobject.Money;
import com.ordering.system.commondomain.valueobject.OrderId;
import com.ordering.system.paymentdataaccess.payment.entity.PaymentEntity;
import com.ordering.system.paymentdomaincore.entity.Payment;
import com.ordering.system.paymentdomaincore.valueobject.PaymentId;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {

    public PaymentEntity paymentToPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId().getValue())
                .customerId(payment.getCustomerId().getValue())
                .orderId(payment.getOrderId().getValue())
                .price(payment.getPrice().getAmount())
                .status(payment.getPaymentStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        return Payment.builder()
                .paymentId(new PaymentId(paymentEntity.getId()))
                .customerId(new CustomerId(paymentEntity.getCustomerId()))
                .orderId(new OrderId(paymentEntity.getOrderId()))
                .price(new Money(paymentEntity.getPrice()))
                .createdAt(paymentEntity.getCreatedAt())
                .build();
    }

}
