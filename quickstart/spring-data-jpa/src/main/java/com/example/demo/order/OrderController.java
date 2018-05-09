package com.example.demo.order;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@EnableEurekaClient
@RestController
@RequestMapping("/order")
public class OrderController {

    public static void main(String[] args) {
        SpringApplication.run(OrderController.class, args);
    }
    
	@Autowired
    OrderRepository repo;
    
	@RequestMapping("add")
    public String add(@RequestParam long sellerId
            , @RequestParam long userId, @RequestParam BigDecimal amount ) {
        Order t = new Order();
        t.setSellerId(sellerId);
        t.setUserId(userId);
        t.setAmount(amount);
        t.setDate(new java.util.Date());
        t = repo.save(t);
        
        return t.toString();
    }
	
	@RequestMapping("count")
    public String count() {
        long ishas = repo.count();
        return ishas+"";
    }
    
}
