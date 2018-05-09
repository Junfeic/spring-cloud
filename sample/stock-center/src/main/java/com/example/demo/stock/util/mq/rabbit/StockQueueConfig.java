package com.example.demo.stock.util.mq.rabbit;

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

import com.example.demo.stock.entity.Stock;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
@Profile({"stock-queue"})
@Configuration
public class StockQueueConfig {

	@Bean
	public TopicExchange topic() {
		return new TopicExchange("sample.stock.topic");
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue stockQueue() {
			return new Queue("sample.stock.stock", true, false, false, new HashMap<>());
		}

		@Bean
		public Binding bindingStock(TopicExchange topic, Queue stockQueue) {
			return BindingBuilder.bind(stockQueue).to(topic).with("*.*");
		}

		@Bean
		public StockQueueReceiver stockReceiver() {
			return new StockQueueReceiver(1);
		}

	}

	@Profile("order-receiver")
	private static class OrderReceiverConfig {

		@Bean
		public FanoutExchange fanout() {
			return new FanoutExchange("sample.order.fanout");
		}

		@Bean
		public Queue  orderQueue() {
			return QueueBuilder.durable("sample.stock.order").build();
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

	/* Bind to payment center */
	@Profile("payment-receiver")
	private static class PaymentReceiverConfig {

		@Bean
		public DirectExchange direct() {
			return new DirectExchange("sample.payment.direct");
		}

		@Bean
		public Queue paymentQueue() {
			return new Queue("sample.stock.payment", true, false, false, new HashMap<>());
		}

		@Bean
		public Binding bindingStockCancel(DirectExchange direct, Queue paymentQueue) {
			return BindingBuilder.bind(paymentQueue).to(direct).with("CANCEL");
		}

		@Bean
		public PaymentQueueReceiver stockReceiver() {
			return new PaymentQueueReceiver(1);
		}

	}
	
	@Profile("sender")
	@Bean
	public StockQueueSender<Stock> sender() {
		return new StockQueueSender<Stock>();
	}

}
