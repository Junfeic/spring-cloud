# spring-cloud
A quickstart of spring-cloud-2.0.1.Finchley.M9 and a sample demo artifact composed of 3 modules.

# quickstart:

application	port	boot location  ==> dependencies	memo

consul-server	8500	E:\consul_1.0.7_windows_amd64>consul.exe agent -dev  ==> 	none，  -http-port=1500 to change	

consul-client	2511	mvn spring-boot:run	 ==> consul-server	

consul-consumer	2611		 ==> consul-server, consul-client	

eureka-server	1001, 1002, 1003	 -Dspring.profiles.active=peer1, 2		

eureka-client	1101, 1102	 -Dserver.port=1102	 ==> eureka-server	

eureka-consumer	2101		 ==> eureka-server, eureka-client	

eureka-consumer-feign	2301		 ==> eureka-server, eureka-client	

eureka-consumer-feign-hystrix	2311			

eureka-consumer-ribbon	2201		 ==> eureka-server, eureka-client	

eureka-consumer-ribbon-hystrix	2211		 ==> eureka-server, eureka-client	

eureka-consumer-ribbon-spring-config	2201		 ==> eureka-server, eureka-client, spring-config-client, spring-config-server	

hystrix-dashboard	1301		 ==> hystrix project to be monitored	

spring-config			 ==> vm_100(git server)	

spring-config-client	1221		 ==> eureka-server, eureka-client, spring-config-server	

spring-config-client-bus-amqp	1232		 ==> eureka-server, spring-config-server	post http://localhost:1221/actuator/bus-refresh

spring-config-server	1201		 ==> eureka-server, eureka-client	

turbine	1400, 1401		 ==> eureka-server	

service-zuul	1769			

spring-amqp	2800			 ==> rabbitmq

spring-data-jpa	2700			 ==>  mysql

sample-order-center	2700		 ==> eureka-server, rabbitmq
sample-stock-center	2710
sample-payment-center	2720


# Sample:

$ cd client-api && mvn install
$ mvn spring-boot:run to start all the center services.

[order-center]$ mvn spring-boot:rum -Dspring.profiles.active=order-queue,receiver,stock-receiver,payment-receiver -Dserver.port=2703

[stock-center]$ mvn spring-boot:rum -Dspring.profiles.active=stock-queue,receiver,order-receiver,payment-receiver -Dserver.port=2713

[payment-center]$ mvn spring-boot:rum -Dspring.profiles.active=payment-queue,receiver,order-receiver-Dserver.port=2723

Message queue routing:
order-center == fanout sample.order.fanout == []
  -- sample.order.order -- order-center/receiver []
  -- sample.stock.order -- stock-center/order-receiver []
  -- sample.payment.order -- payment-center/order-receiver []

stock-center == topic sample.stock.topic == [ADD, INCREASE, DECREASE x SUCCESS, FAILED]
  -- sample.stock.stock -- stock-center/receiver [*.*]
  -- sample.order.stock -- order-center/stock-receiver [*.FAILED]

payment-center == direct sample.payment.direct == [ADD, PAY, CANCEL]
  -- sample.payment.payment -- payment-center/receiver [ADD, PAY, CANCEL]
  -- sample.stock.payment -- stock-center/payment-receiver [CANCEL]
  -- sample.order.payment -- order-center/payment-receiver [PAY, CANCEL]
 
