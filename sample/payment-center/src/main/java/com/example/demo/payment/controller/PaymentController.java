package com.example.demo.payment.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.service.PaymentService;
import com.example.demo.payment.util.mq.rabbit.PaymentQueueSender;

@EnableWebMvc
@RestController
@RequestMapping("/pay")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	@Autowired
	PaymentQueueSender<Payment> paymentQueueSender;
	
	@RequestMapping("add")
    public String add(Payment payment) throws Exception {
		// param process
		payment.setCreateTime(new java.util.Date());
		payment = paymentService.addTicket(payment);
		
        // MQ publishMessage(t)
		paymentQueueSender.send(payment,"ADD");
    	
        return payment.toString()+": "+payment.getId();
    }

	@RequestMapping("pay")
    public String increase(@RequestParam int paymentId, int payChannel, String seriaNumber, Date payTime) {
		Payment payment = null;
		payment = paymentService.payTicket(paymentId, payChannel, seriaNumber, payTime);
		paymentQueueSender.send(payment, "PAY");
        return payment.toString()+": "+payment.getId();
    }

	@RequestMapping("cancel")
    public String decrease(@RequestParam int paymentId) throws Exception {
		Payment payment = paymentService.cancelTicket(paymentId);
		paymentQueueSender.send(payment, "CANCEL");
        return payment.toString()+": "+payment.getId();
    }
	
	@RequestMapping("count")
    public String count() {
        long ishas = paymentService.getCount();
        return ishas+"";
    }
    
}
