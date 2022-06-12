package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.stock.StockBalance;

@Repository
public interface StockBalanceRepository extends JpaRepository<StockBalance, Long> {

	@Query("Select sb From StockBalance sb where sb.product.id =:prodId")
	public StockBalance getStockBalForSelProduct(long prodId);
	
}
