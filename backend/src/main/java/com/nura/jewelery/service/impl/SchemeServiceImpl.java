package com.nura.jewelery.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.entity.scheme.Scheme;
import com.nura.jewelery.repository.SchemeRepository;
import com.nura.jewelery.service.OfferService;
import com.nura.jewelery.service.SchemeService;

@Service
@Transactional
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	private SchemeRepository schemeRepository;

	@Autowired
	private OfferService offerService;

	@Override
	public Scheme saveScheme(Scheme scheme) {
		return schemeRepository.save(scheme);
	}

	@Override
	public Optional<Scheme> getSchemeBsdOnID(long schemeID) {
		return schemeRepository.findById(schemeID);
	}

	@Override
	public List<Scheme> getActiveSchemes() {
		return schemeRepository.activeSchemes();
	}

//	@Override
//	public List<Scheme> getActiveSchemesForCustomerID(long custID) {
//		return schemeRepository.getAllSchemsForCustID(custID);
//	}

//	@Override
//	public List<Scheme> getAllSchemesForCustomerID(long custID) {
//		return schemeRepository.getAllSchemsForCustID(custID);
//	}

	@Override
	public void updateSchemeOffers(long schemeId, long offerId) {
		Optional<Scheme> scheme = schemeRepository.findById(schemeId);
		if (!scheme.isPresent()) {
			throw new NotFoundException("Scheme not found for id :" + schemeId);
		}

		Offer offer = offerService.getOfferByID(offerId);
		List<Offer> offers = scheme.get().getOffers();
		offers.add(offer);
		scheme.get().setOffers(offers);

		schemeRepository.save(scheme.get());

	}

	@Override
	public List<Offer> getOffersBsdOnSchemeID(long schemeID) {
		return schemeRepository.getOffersBsdOnSchemeID(schemeID).getOffers();
	}

}
