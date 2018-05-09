package com.example.demo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.stock.entity.Stock;
import com.example.demo.stock.service.StockRepository;
import com.example.demo.stock.service.StockService;



@RestController
@EnableDiscoveryClient
@RequestMapping("/api/stock")
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository repo;
    
	@Override
	public Stock addStock(Stock stock) throws Exception {
		if(stock.getId() == 0) {
			return repo.save(stock);
		}
		
		Stock t = repo.findById(stock.getId()).get();
		if(t!=null) {
			throw new Exception("Stock already exist; try increase or decrease instead");
		}
		
		return repo.save(stock);
	}

	@Override
	@GetMapping("getCount")
    public long getCount() {
		return repo.count();
	}

	@Override
	public Stock increase(int stockId, int num) throws Exception {
		Stock t = repo.findById(stockId).get();
		if(t == null) {
			throw new Exception("Stock not exist");
		}
		
		t.setBalance(t.getBalance() + num);
		t.setUpdateTime(new java.util.Date());
		return repo.save(t);
	}

	@Override
	public Stock decrease(int stockId, int num) throws Exception {
		Stock t = repo.findById(stockId).get();
		if(t == null) {
			throw new Exception("Stock not exist");
		}
		
		int balance = t.getBalance();
		if(balance < num) {
			throw new Exception("No enough balance");
		}
		
		t.setBalance(balance - num);
		return repo.save(t);
	}


}
