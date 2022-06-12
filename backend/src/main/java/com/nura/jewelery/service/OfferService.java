package com.nura.jewelery.service;

import com.nura.jewelery.entity.offers.Offer;

public interface OfferService {

	public Offer saveOffer(Offer offer);

	public Offer getOfferByID(Long offerID);

	public boolean isActive(Long offerID);

}
