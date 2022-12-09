package com.ordering.system.paymentdomaincore;

import java.util.List;

import com.ordering.system.paymentdomaincore.entity.CreditEntry;
import com.ordering.system.paymentdomaincore.entity.CreditHistory;
import com.ordering.system.paymentdomaincore.entity.Payment;
import com.ordering.system.paymentdomaincore.event.PaymentEvent;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages);
}
