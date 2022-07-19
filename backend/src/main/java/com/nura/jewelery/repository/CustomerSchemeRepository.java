package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.scheme.CustomerScheme;

@Repository
public interface CustomerSchemeRepository extends JpaRepository<CustomerScheme, Long> {

	@Query("select c from CustomerScheme c where c.id =:id")
	public CustomerScheme getCustomerSchemeBsdOnID(long id);

}
