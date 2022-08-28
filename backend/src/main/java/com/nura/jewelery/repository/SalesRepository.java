package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.sales.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

	@Query("From Sales s where s.customer.custId =:custID")
	public List<Sales> getSalesBsdOnCustomerID(long custID, Sort sort);
	
}
