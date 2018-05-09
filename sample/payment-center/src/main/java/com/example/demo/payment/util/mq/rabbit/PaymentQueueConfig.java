package com.example.demo.payment.util.mq.rabbit;

import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.payment.entity.Payment;

/**
 * @author Gary Russell
 * @author Scott Deeg
 * @author Junhong Chen
 */
@Profile({"payment-queue"})
@Configuration
public class PaymentQueueConfig {

	@Bean
	public DirectExchange direct() {
		return new DirectExchange("sample.payment.direct");
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue paymentQueue() {
			return new Queue("sample.payment.payment", true, false, false, new HashMap<>());
		}

		@Bean
		public Binding bindingStockAdd(DirectExchange direct, Queue paymentQueue) {
			return BindingBuilder.bind(paymentQueue).to(direct).with("ADD");
		}

		@Bean
		public Binding bindingStockPay(DirectExchange direct, Queue paymentQueue) {
			return BindingBuilder.bind(paymentQueue).to(direct).with("PAY");
		}

		@Bean
		public Binding bindingStockCancel(DirectExchange direct, Queue paymentQueue) {
			return BindingBuilder.bind(paymentQueue).to(direct).with("CANCEL");
		}

		@Bean
		public PaymentQueueReceiver paymentReceiver() {
			return new PaymentQueueReceiver(1);
		}

	}

	/* Bind to order center */
	@Profile("order-receiver")
	private static class OrderReceiverConfig {

		@Bean
		public FanoutExchange fanout() {
			return new FanoutExchange("sample.order.fanout");
		}

		@Bean
		public Queue  orderQueue() {
			return QueueBuilder.durable("sample.payment.order").build();
		}

		@Bean
		public Binding bindingOrder(FanoutExchange fanout, Queue orderQueue) {
			return BindingBuilder.bind(orderQueue).to(fanout);
		}

		@Bean
		public OrderQueueReceiver orderReceiver() {
			return new OrderQueueReceiver(1);
		}

	}
	
	@Profile("sender")
	@Bean
	public PaymentQueueSender<Payment> sender() {
		return new PaymentQueueSender<Payment>();
	}

}
