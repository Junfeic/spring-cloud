package com.example.demo.stock.service;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.stock.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {

}
