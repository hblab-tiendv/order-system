package com.ordering.system.ordercontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.ordering.system.orderdataaccess", "com.ordering.system.commondataaccess"})
@EntityScan(basePackages = { "com.ordering.system.orderdataaccess", "com.ordering.system.commondataaccess" })
@SpringBootApplication(scanBasePackages = "com.ordering.system")
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
