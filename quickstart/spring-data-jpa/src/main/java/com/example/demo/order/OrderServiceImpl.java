package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
		return null;
	}

	@Override
	@GetMapping("getCount")
    public long getCount() {
		return repo.count();
	}

}

interface OrderService {
	Order addOrder(Order order);
	Order payOrder(int orderId);
	long getCount();
}