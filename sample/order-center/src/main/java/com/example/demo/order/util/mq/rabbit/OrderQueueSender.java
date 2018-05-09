package com.example.demo.order.util.mq.rabbit;

import java.io.Serializable;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
@Service
public class OrderQueueSender<T extends Serializable> {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private FanoutExchange fanout;

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
		template.convertAndSend(fanout.getName(), "", JSONObject.toJSONString(data));
	}

}
