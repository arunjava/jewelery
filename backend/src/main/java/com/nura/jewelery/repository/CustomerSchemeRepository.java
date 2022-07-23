package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.dto.customer.CustomerSchemeDTO;
import com.nura.jewelery.entity.scheme.CustomerScheme;

@Repository
public interface CustomerSchemeRepository extends JpaRepository<CustomerScheme, Long> {

	@Query("select c from CustomerScheme c where c.id =:id")
	public CustomerScheme getCustomerSchemeBsdOnID(long id);

	@Query(value = "select new com.nura.jewelery.dto.customer.CustomerSchemeDTO(cs.id, cs.customer.custId, cs.scheme.id, cs.uom.id, cs.exchangeVal ,cs.startDate, cs.endDate) from CustomerScheme cs where cs.customer.custId =:custId and cs.isActive = true")
	public List<CustomerSchemeDTO> getActiveCustomerSchemeBsdOnID(long custId);
}
