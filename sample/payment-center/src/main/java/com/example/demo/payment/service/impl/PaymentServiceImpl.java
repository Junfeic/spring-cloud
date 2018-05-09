package com.example.demo.payment.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.service.PaymentRepository;
import com.example.demo.payment.service.PaymentService;



@RestController
@EnableDiscoveryClient
@RequestMapping("/api/payment")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository repo;

	@GetMapping("count")
	@Override
	public long getCount() {
		return repo.count();
	}

	@Override
	public Payment addTicket(Payment payment) {
		return repo.save(payment);
	}

	@Override
	public Payment payTicket(int paymentId, int payChannel, String seriaNumber, Date payTime) {
		Payment payment = repo.findById(paymentId).get();
		payment.setPayChannel(payChannel);
		payment.setSeriaNumber(seriaNumber);
		payment.setPayTime(payTime);
		payment.setState(Payment.PAYED);
		return repo.save(payment);
	}

	@Override
	public Payment cancelTicket(int paymentId) {
		Payment payment = repo.findById(paymentId).get();		
		payment.setState(Payment.CANCELED);
		return repo.save(payment);
	}
    
}
