package com.nura.jewelery.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.product.Pricing;
import com.nura.jewelery.entity.sales.Sales;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.entity.uom.UOMConversion;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.PricingRepository;
import com.nura.jewelery.repository.SalesRepository;
import com.nura.jewelery.repository.UOMConversionRepository;
import com.nura.jewelery.repository.UOMRepository;
import com.nura.jewelery.service.SalesService;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesRepository salesRepo;

	@Autowired
	private UOMRepository uomRepo;

	@Autowired
	private UOMConversionRepository uomConversionRepo;

	@Autowired
	private PricingRepository pricingRepo;

	@Override
	public Sales saveSales(Sales sale) {
		Sales saleUpdate = sale;
		double salesAmt;
		double qty = sale.getQty();
		Optional<UOM> uom = uomRepo.findById(sale.getUom().getId());
		if (uom.isPresent()) {
			Pricing pricing = pricingRepo.getLatestPricingForProduct(sale.getProduct().getProductId());
			if (pricing.getUom().getId().equals(uom.get().getId())) {
				salesAmt = qty * sale.getSellingPrice();
				sale.setProfitAmt(salesAmt - (qty * sale.getCostPrice()));
			} else {
				UOMConversion uomConversion = uomConversionRepo.getConversion(sale.getUom().getId(),
						pricing.getUom().getId());
				salesAmt = qty * sale.getSellingPrice() * uomConversion.getConversionVal();
				sale.setProfitAmt(salesAmt - (qty * sale.getCostPrice()));
			}

			sale.setSoldAmt(salesAmt);
			return salesRepo.save(saleUpdate);
		} else {
			throw new NotFoundException("Invalid UOM selected id ::" + sale.getUom().getId());
		}
	}

}
