package com.example.eureka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String dc() {
    	/* Ribbon will auto contact url.
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client-service-provider");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);*/
        String res = restTemplate.getForObject("http://eureka-client-service-provider/dc", String.class);
        return res;
    }
}
