package com.ordering.system.paymentcontainer;

import com.ordering.system.paymentdomaincore.PaymentDomainService;
import com.ordering.system.paymentdomaincore.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
