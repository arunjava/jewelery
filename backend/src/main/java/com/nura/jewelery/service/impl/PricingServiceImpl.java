package com.nura.jewelery.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.product.Pricing;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.PricingRepository;
import com.nura.jewelery.service.PricingService;

@Service
@Transactional
public class PricingServiceImpl implements PricingService {

	@Autowired
	private PricingRepository pricingRepo;

	@Override
	public Pricing save(Pricing pricing) {
		return pricingRepo.save(pricing);
	}

	@Override
	public Pricing getPricing(long productID) {
		return pricingRepo.getLatestPricingForProduct(productID);
	}

	@Override
	public Pricing getPricingBsdOnProdNUomID(long prodID, long uomID) {
		Pricing pricing = pricingRepo.getLatestPricingBsdOnProdIDNUOmID(prodID, uomID);
		if (pricing == null) {
			throw new NotFoundException("Pricing not found for product id :" + prodID);
		}

		return pricing;

	}

}
