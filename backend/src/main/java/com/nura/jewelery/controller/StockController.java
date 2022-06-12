package com.nura.jewelery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.stock.StockInDTO;
import com.nura.jewelery.entity.stock.StockIn;
import com.nura.jewelery.mapper.StockInMapper;
import com.nura.jewelery.service.StockService;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {


	@Autowired
	private StockService stockService;
	
	@Autowired
	private StockInMapper stockInMapper;
	
	@PostMapping("/stockin")
	public StockIn stockIn(@RequestBody StockInDTO stockIn)  {
		return stockService.stockIn(stockInMapper.dtoToDomain(stockIn));
	}
	
	@GetMapping("/stockin")
	public List<StockInDTO> getListOfStockIn() {
		return stockInMapper.domainToDTOs(stockService.getStockIns());
		
	}
	
}
