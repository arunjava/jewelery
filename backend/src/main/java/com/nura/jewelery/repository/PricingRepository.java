package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.product.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

	@Query("Select p From Pricing p where p.product.productId =:productId and p.id ="
			+ "(select max(pl.id) From Pricing pl)")
	public Pricing getLatestPricingForProduct(long productId);
	
	@Query("Select p From Pricing p where p.isActive = true and p.product.productId =:productId and p.uom.id =:uomId and p.id = "
			+ "(select max(pl.id) From Pricing pl)")
	public Pricing getLatestPricingBsdOnProdIDNUOmID(long productId, long uomId);
}
