package com.example.demo.payment.service;

import java.util.Date;

import com.example.demo.payment.entity.Payment;

public interface PaymentService {
	
	Payment addTicket(Payment payment);
	
	Payment payTicket(int paymentId, int payChannel, String seriaNumber, Date payTime);
	
	Payment cancelTicket(int paymentId);
	
	long getCount();
}
