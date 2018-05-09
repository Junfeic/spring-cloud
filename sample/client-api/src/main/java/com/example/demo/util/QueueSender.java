package com.example.demo.util;

import java.io.Serializable;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
public class QueueSender<T extends Serializable> {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

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
//		Order o = new Order();
//		o.setMemo(message);
//		template.convertAndSend(queue.getName(), o);
//		System.out.println(" [x] Sent '" + message + "'");
//	}
	
	public void send(T data) {
		template.convertAndSend(queue.getName(), JSONObject.toJSONString(data));
	}

}
