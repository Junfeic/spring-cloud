package com.example.demo.order.util.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.order.entity.Order;


@RabbitListener(queues = "sample.order.order", group="order-demo")
public class OrderQueueReceiver {

	private final int instance;

	public OrderQueueReceiver(int i) {
		this.instance = i;
	}

	@RabbitHandler(isDefault=true)
	public void receive(Message in) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + this.instance + " [x] Received '" + in + "'" +JSONObject.toJSON(in));
		String json = new String(in.getBody());
		System.out.println(json);
		doWork(JSONObject.parseObject(json, Order.class));
		watch.stop();
		System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(Order in) throws InterruptedException {
		String memo = in.getMemo();
		if (memo != null) {
			for (char ch : memo.toCharArray()) {
				if (ch == '.') {
					Thread.sleep(1000);
				}
			}
		}
	}

}