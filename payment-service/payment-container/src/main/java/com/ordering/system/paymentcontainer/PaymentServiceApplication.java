package com.ordering.system.paymentcontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.ordering.system.paymentdataaccess")
@EntityScan(basePackages = "com.ordering.system.paymentdataaccess")
@SpringBootApplication(scanBasePackages = "com.ordering.system")
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
