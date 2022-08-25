package com.nura.jewelery.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.dto.ProductExchangeDTO;
import com.nura.jewelery.entity.ProductExchange;
import com.nura.jewelery.repository.ProductExchangeRepository;
import com.nura.jewelery.service.ProductExchangeService;

@Service
@Transactional
public class ProductExchangeServiceImpl implements ProductExchangeService {

	@Autowired
	private ProductExchangeRepository productExchangeRepo;

	@Override
	public ProductExchange saveProductExchange(ProductExchange prodExchange) {
		return productExchangeRepo.save(prodExchange);
	}

	@Override
	public List<ProductExchangeDTO> getExchangeTotal(long customerID) {
		return productExchangeRepo.getExhangeValBsdOnCustomerID(customerID);
	}

}
