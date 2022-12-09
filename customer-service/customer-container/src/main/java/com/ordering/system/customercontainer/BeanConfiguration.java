package com.ordering.system.customercontainer;

import com.ordering.system.customerdomaincore.CustomerDomainService;
import com.ordering.system.customerdomaincore.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
