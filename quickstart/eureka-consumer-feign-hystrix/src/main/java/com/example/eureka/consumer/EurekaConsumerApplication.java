package com.example.eureka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerApplication {
	
	@FeignClient(value="eureka-client-service-provider", fallback=DcClientHystrix.class)
	public interface DcClient {

	    @GetMapping("/dc")
	    String consumer();

	}

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }
    
    
}
