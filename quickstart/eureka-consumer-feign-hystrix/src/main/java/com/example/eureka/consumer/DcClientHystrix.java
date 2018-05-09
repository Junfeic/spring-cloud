package com.example.eureka.consumer;

import org.springframework.stereotype.Component;

import com.example.eureka.consumer.EurekaConsumerApplication.DcClient;

@Component
public class DcClientHystrix implements DcClient {
    @Override
    public String consumer() {
	    return "服务调用失败";
    }	
}
