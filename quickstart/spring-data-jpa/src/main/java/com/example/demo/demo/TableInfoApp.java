package com.example.demo.demo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.example"})
@EnableWebMvc
@EnableEurekaClient
@RestController
@RequestMapping("/table")
public class TableInfoApp {
    
    private static final Logger logger = Logger.getLogger(TableInfoApp.class);
    
    public static void main(String[] args) {
        SpringApplication.run(TableInfoApp.class, args);
    }
    @Autowired
    TableInfoDao dao;
    
    //Always the same
    @RequestMapping("hi")
    @Bean
    public String home(TableInfoDao dao) {
        logger.info("测试");
        int ishas = dao.getcount();
        return ishas+"";
    }
    
    @RequestMapping("add")
    public String add(@RequestParam String name
            , @RequestParam String text) {
        TableInfo t = new TableInfo();
        t.setTableid(1);
        t.setTablename(name);
        t.setTabletext(text);
        t = dao.save(t);
        return t.toString();
    }
    
    //In time value returned.
    @RequestMapping("count")
    public String count() {
        logger.info("测试");
        int ishas = dao.getcount();
        return ishas+"";
    }
    
}