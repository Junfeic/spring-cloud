package com.example.demo.order.util.mq.rabbit;

import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.order.entity.Order;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
@Profile({"order-queue"})
@Configuration
public class OrderQueueConfig {

	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("sample.order.fanout");
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue  orderQueue() {
			return QueueBuilder.durable("sample.order.order").build();
		}

		@Bean
		public Binding binding(FanoutExchange fanout, Queue orderQueue) {
			return BindingBuilder.bind(orderQueue).to(fanout);
		}

		@Bean
		public OrderQueueReceiver receiver1() {
			return new OrderQueueReceiver(1);
		}

	}

	/* Bind to stock center */
	@Profile("stock-receiver")
	private static class StockReceiverConfig {

		@Bean
		public TopicExchange topic() {
			return new TopicExchange("sample.stock.topic");
		}

		@Bean
		public Queue stockQueue() {
			return new Queue("sample.order.stock.failed", true, false, false, new HashMap<>());
		}

		@Bean
		public Binding bindingStock(TopicExchange topic, Queue stockQueue) {
			return BindingBuilder.bind(stockQueue).to(topic).with("*.FAILED");
		}

		@Bean
		public StockQueueReceiver receiverStock() {
			return new StockQueueReceiver(1);
		}

	}

	/* Bind to payment center */
	@Profile("payment-receiver")
	private static class PaymentReceiverConfig {

		@Bean
		public DirectExchange direct() {
			return new DirectExchange("sample.payment.direct");
		}

		@Bean
		public Queue paymentQueue() {
			return new Queue("sample.order.payment", true, false, false, new HashMap<>());
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
	
	@Profile("sender")
	@Bean
	public OrderQueueSender<Order> sender() {
		return new OrderQueueSender<Order>();
	}

}
