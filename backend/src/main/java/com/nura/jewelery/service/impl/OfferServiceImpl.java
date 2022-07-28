package com.nura.jewelery.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.exception.AlreadyExistException;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.OfferRepository;
import com.nura.jewelery.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepo;

	@Override
	public Offer saveOffer(Offer offer) {
		if (offerRepo.countByOfferCode(offer.getOfferCode()) > 0) {
			throw new AlreadyExistException("Offer code already exist");
		}

		return offerRepo.save(offer);
	}

	@Override
	@Transactional
	public Offer getOfferByID(Long offerID) {
		Optional<Offer> offer = offerRepo.findById(offerID);
		if (offer.isPresent()) {
			return offer.get();
		} else {
			throw new NotFoundException("Offer not exist for id :" + offerID);
		}

	}

	@Override
	public boolean isActive(Long offerID) {
		Optional<Offer> offer = offerRepo.findById(offerID);
		if (offer.isPresent()) {
			return offer.get().isActvie();
		} else {
			throw new NotFoundException("Offer not exist for id :" + offerID);
		}
	}

	@Override
	public List<Offer> getAllOffers() {
		return offerRepo.findAll();
	}

}
