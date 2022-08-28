package com.nura.jewelery.service;

import java.util.List;

import com.nura.jewelery.dto.sales.SalesDTO;
import com.nura.jewelery.entity.sales.Sales;

public interface SalesService {

	public Sales saveSales(SalesDTO sale);
	
	public Sales preCalculate(SalesDTO salesDTO);
	
	public List<Sales> getSalesBsdOnCutomerID(long custID);

}
