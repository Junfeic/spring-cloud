package com.example.demo.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.order.entity.Order;
import com.example.demo.order.service.OrderRepository;
import com.example.demo.order.service.OrderService;


@RestController
@EnableDiscoveryClient
@RequestMapping("/api/order")
public class OrderServiceImpl implements OrderService {

	@Autowired
    OrderRepository repo;
    
	@Override
	public Order addOrder(Order order) {
		return repo.save(order);
	}

	@Override
	public Order payOrder(int orderId) {
		Order t = repo.findById(orderId).get();
		t.setState(Order.STATE_PAYED);
		return repo.save(t);
	}

	@Override
	@GetMapping("getCount")
    public long getCount() {
		return repo.count();
	}

}
