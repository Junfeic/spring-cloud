package com.example.demo.util;

import java.io.Serializable;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.core.Message;
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSONObject;

public abstract class QueueReceiver<T extends Serializable> {

	private final int instance;

	public QueueReceiver(int i) {
		this.instance = i;
	}

	@SuppressWarnings("unchecked")
	@RabbitHandler
	public void receive(Message<T> in) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + this.instance + " [x] Received '" + in + "'" +JSONObject.toJSON(in));
		String json = (String) in.getBpdy();
		T o = JSONObject.parseObject(json, T);
		doWork(o);
		watch.stop();
		System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	protected abstract void doWork(T in) throws Exception;

}