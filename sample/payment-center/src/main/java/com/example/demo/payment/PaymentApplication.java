package com.example.demo.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@EnableEurekaClient
@SpringBootApplication
@EnableScheduling
public class PaymentApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PaymentApplication.class, args);
    }

}