package com.example.demo.payment.util.mq.rabbit;

import java.io.Serializable;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
@Service
public class PaymentQueueSender<T extends Serializable> {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private DirectExchange direct;

	int dots = 0;

	int count = 0;

//	@Scheduled(fixedDelay = 1000, initialDelay = 500)
//	public void send() {
//		StringBuilder builder = new StringBuilder("Hello");
//		if (dots++ == 3) {
//			dots = 1;
//		}
//		for (int i = 0; i < dots; i++) {
//			builder.append('.');
//		}
//		builder.append(Integer.toString(++count));
//		String message = builder.toString();
//		Stock o = new Stock();
//		o.setMemo(message);
//		template.convertAndSend(queue.getName(), o);
//		System.out.println(" [x] Sent '" + message + "'");
//	}
	
	public void send(T data, String key) {
		template.convertAndSend(direct.getName(), key,  JSONObject.toJSONString(data));
	}

}