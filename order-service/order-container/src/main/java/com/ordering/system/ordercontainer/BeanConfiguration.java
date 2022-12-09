package com.ordering.system.ordercontainer;

import com.ordering.system.orderdomaincore.OrderDomainService;
import com.ordering.system.orderdomaincore.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
