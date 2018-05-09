package com.example.demo.order.service;

import com.example.demo.order.entity.Order;

public interface OrderService {
	Order addOrder(Order order);
	Order payOrder(int orderId);
	long getCount();
}
