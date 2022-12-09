package com.ordering.system.orderapplicationservice.ports.input.message.listener.payment;

import com.ordering.system.orderapplicationservice.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
