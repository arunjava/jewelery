package com.nura.jewelery.service;

import com.nura.jewelery.entity.product.Pricing;

public interface PricingService {

	public Pricing save(Pricing pricing);

	public Pricing getPricing(long productID);

}
