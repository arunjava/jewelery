package com.nura.jewelery.service;

import java.util.List;

import com.nura.jewelery.dto.ProductExchangeDTO;
import com.nura.jewelery.entity.ProductExchange;

public interface ProductExchangeService {

	public ProductExchange saveProductExchange(ProductExchange prodExchange);

	public List<ProductExchangeDTO> getExchangeTotal(long customerID);

}
