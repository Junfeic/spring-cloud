package com.example.demo.stock.util.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.payment.Payment;


@RabbitListener(queues = "#{paymentQueue.name}")
public class PaymentQueueReceiver {

	private final int instance;

	public PaymentQueueReceiver(int i) {
		this.instance = i;
	}

	@RabbitHandler(isDefault=true)
	public void receive(Message in) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + this.instance + " [x] Received '" + in + "'" + JSONObject.toJSON(in));
		String json = new String(in.getBody());
		doWork(JSONObject.parseObject(json, Payment.class));
		watch.stop();
		System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(Payment in) throws InterruptedException {
		// unlock stock
		String memo = in.getSeriaNumber();
		if(memo != null){
		for (char ch : memo.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
		}
	}

}