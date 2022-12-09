package com.ordering.system.paymentapplicationservice.ports.input.message.listener;

import com.ordering.system.paymentapplicationservice.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
