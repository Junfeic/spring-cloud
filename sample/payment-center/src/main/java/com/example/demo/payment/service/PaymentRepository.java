package com.example.demo.payment.service;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.payment.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
