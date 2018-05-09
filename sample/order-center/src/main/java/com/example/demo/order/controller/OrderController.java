package com.example.demo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.order.entity.Order;
import com.example.demo.order.service.OrderService;
import com.example.demo.order.util.mq.rabbit.OrderQueueSender;

@EnableWebMvc
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	OrderQueueSender<Order> orderQueueSender;
	
	@RequestMapping("add")
    public String add(Order order ) {
		// param process
//        Order t = new Order();
//        t.setSellerId(sellerId);
//        t.setUserId(userId);
//        t.setAmount(amount);
		order.setDate(new java.util.Date());
        // service
		order = orderService.addOrder(order);
        
        // MQ publishMessage(t)
        orderQueueSender.send(order);
    	
        return order.toString()+": "+order.getId();
    }
	
	@RequestMapping("count")
    public String count() {
        long ishas = orderService.getCount();
        return ishas+"";
    }
    
}
