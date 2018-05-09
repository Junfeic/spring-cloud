package com.example.demo.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.stock.entity.Stock;
import com.example.demo.stock.service.StockService;
import com.example.demo.stock.util.mq.rabbit.StockQueueSender;

@EnableWebMvc
@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	StockService stockService;
	@Autowired
	StockQueueSender<Stock> stockQueueSender;
	
	@RequestMapping("add")
    public String add(Stock stock) throws Exception {
		// param process
		stock.setCreateTime(new java.util.Date());
		
        // service
		try{
		    stock = stockService.addStock(stock);
		} catch(Exception e) {
			stockQueueSender.send(stock,"ADD.FAILED");
			throw e;
		}
        // MQ publishMessage(t)
		stockQueueSender.send(stock,"ADD.SUCCESS");
    	
        return stock.toString()+": "+stock.getId();
    }

	@RequestMapping("increase")
    public String increase(@RequestParam int stockId, @RequestParam int num) throws Exception {
		Stock stock = null;
		try{
			stock = stockService.increase(stockId, num);
		} catch(Exception e) {
			stockQueueSender.send(new Stock(stockId), "INCREASE.FAILED");
			throw e;
		}
		stockQueueSender.send(stock, "INCREASE.SUCCESS");
        return stock.toString()+": "+stock.getId();
    }

	@RequestMapping("decrease")
    public String decrease(@RequestParam int stockId, @RequestParam int num) throws Exception {
		Stock stock = null;
		try{
			stock = stockService.decrease(stockId, num);
		} catch(Exception e) {
			stockQueueSender.send(new Stock(stockId), "DECREASE.FAILED");
			throw e;
		}
		stockQueueSender.send(stock, "DECREASE.SUCCESS");
        return stock.toString()+": "+stock.getId();
    }
	
	@RequestMapping("count")
    public String count() {
        long ishas = stockService.getCount();
        return ishas+"";
    }
    
}
