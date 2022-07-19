package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.offers.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

//	@Query("select o from Offer o where ")
//	List<Offer> getOffersBsdOnSchemeID(long schemeID);
	
}
