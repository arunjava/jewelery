package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.uom.UOMCategory;

@Repository
public interface UOMCategoryRepository extends JpaRepository<UOMCategory, Long> {
	
	@Query("select uom_cat from UOMCategory uom_cat where uom_cat.uomCat like %:uomCat%")
	public List<UOMCategory> findByName(String uomCat);

}
