package com.nura.jewelery.service;

import java.util.List;

import com.nura.jewelery.entity.stock.StockIn;

public interface StockService {

	public StockIn stockIn(StockIn stockIn);
	
	public List<StockIn> getStockIns();
	
}
