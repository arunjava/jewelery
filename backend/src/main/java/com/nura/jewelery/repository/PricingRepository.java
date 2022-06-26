package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.product.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

	@Query("Select p From Pricing p where p.product.productId =:productId")
	public Pricing getLatestPricingForProduct(long productId);
}
