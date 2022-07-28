package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.uom.UOM;

@Repository
public interface UOMRepository extends JpaRepository<UOM, Long> {

	@Query("select u from UOM u where u.uomCategory.id =:uomCatID")
	public List<UOM> getUOMsBsdOnUOMCatID(long uomCatID);

}
