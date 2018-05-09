package com.example.demo.stock.service;

import com.example.demo.stock.entity.Stock;

public interface StockService {
	
	Stock addStock(Stock stock) throws Exception;
	
	Stock increase(int stockId, int num) throws Exception;
	
	Stock decrease(int stockId, int num) throws Exception;
	
	long getCount();
}
