package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

//	@Query(value = "select c from CustomerScheme c where c.customer.custId =:custId and c.scheme.prodCategory.categoryId =:prodCatID ")
//	public List<CustomerScheme> getActiveCustomerSchemeBsdOnIDAndProdCatID(long custId, long prodCatID);

	@Query(value = "select new com.nura.jewelery.dto.customer.CustomerSchemeDTO(cs.id, cs.customer.custId, cs.scheme.id, cs.exchangeUOM.id, cs.exchangeVal ,cs.startDate, cs.endDate) from CustomerScheme cs where cs.endDate <= CURRENT_DATE and cs.customer.custId =:custId and cs.isActive = true and cs.scheme.prodCategory.categoryId =:prodCatID")
	public List<CustomerSchemeDTO> getActiveCustomerSchemeBsdOnIDAndProdCatID(long custId, long prodCatID);

	@Modifying
	@Query("update CustomerScheme cs set cs.isActive =:status where cs.id =:id")
	public void updateCustomerSchemeStatus(boolean status, long id);
}
