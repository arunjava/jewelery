package com.nura.jewelery.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.stock.StockBalance;
import com.nura.jewelery.entity.stock.StockIn;
import com.nura.jewelery.repository.StockBalanceRepository;
import com.nura.jewelery.repository.StockInRepository;
import com.nura.jewelery.service.StockService;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockInRepository stockInRepo;

	@Autowired
	private StockBalanceRepository stockBalRepo;

	@Override
	public StockIn stockIn(StockIn stockIn) {
		StockBalance stockBal = stockBalRepo.getStockBalForSelProduct(stockIn.getProduct().getProductId());
		if(stockBal == null)  {
			stockBal = new StockBalance();
			stockBal.setBarCode(stockIn.getBarCode());
			stockBal.setBatch(stockIn.getBatch());
			stockBal.setProduct(stockIn.getProduct());
			stockBal.setQrCode(stockIn.getQrCode());
			stockBal.setStockBal(stockIn.getQty());
			stockBal.setUom(stockIn.getUom());
		} else {
			stockBal.setStockBal(stockBal.getStockBal() + stockIn.getQty());
		}
		stockBalRepo.save(stockBal);
		return stockInRepo.save(stockIn);
	}

	@Override
	public List<StockIn> getStockIns() {
		return stockInRepo.findAll();
	}

}
