package com.example.demo.order.service;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.order.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
