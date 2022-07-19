package com.nura.jewelery.service;

import com.nura.jewelery.dto.sales.SalesDTO;
import com.nura.jewelery.entity.sales.Sales;

public interface SalesService {

	public Sales saveSales(SalesDTO sale);

}
