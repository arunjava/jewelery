package com.nura.jewelery.service;

import java.util.List;
import java.util.Optional;

import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.entity.scheme.Scheme;

public interface SchemeService {

	public Scheme saveScheme(Scheme scheme);

	public Optional<Scheme> getSchemeBsdOnID(long schemeID);

	public List<Scheme> getActiveSchemes();

//	public List<Scheme> getActiveSchemesForCustomerID(long custID);

//	public List<Scheme> getAllSchemesForCustomerID(long custID);
	
	public void updateSchemeOffers(long schemeId, long offerId);
	
	public List<Offer> getOffersBsdOnSchemeID(long schemeID);

}
