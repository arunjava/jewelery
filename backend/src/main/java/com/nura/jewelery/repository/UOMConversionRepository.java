package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.uom.UOMConversion;

@Repository
public interface UOMConversionRepository extends JpaRepository<UOMConversion, Long> {

	@Query("Select u From UOMConversion u where u.fromUOMID =:fromUOMID and u.toUOMID =:toUOMID")
	public UOMConversion getConversion(long fromUOMID, long toUOMID);

}
