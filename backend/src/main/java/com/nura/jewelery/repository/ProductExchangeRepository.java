package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nura.jewelery.dto.ProductExchangeDTO;
import com.nura.jewelery.entity.ProductExchange;

public interface ProductExchangeRepository extends JpaRepository<ProductExchange, Long> {

	@Query(nativeQuery = true, name = "ProductExchangeDTO")
	public List<ProductExchangeDTO> getExhangeValBsdOnCustomerID(long customerID);

}
