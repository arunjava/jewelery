package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.Scheme;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {

	@Query("select s from Scheme s where s.isActive = true")
	List<Scheme> activeSchemes();

	@Query("select s from Scheme s join s.customers c where c.custId = :custId")
	List<Scheme> getAllSchemsForCustID(long custId);
}
