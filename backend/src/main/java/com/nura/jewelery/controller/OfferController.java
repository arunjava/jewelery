package com.nura.jewelery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.offer.OfferDTO;
import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.mapper.OfferMapper;
import com.nura.jewelery.service.OfferService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {

	@Autowired
	private OfferService offerService;
	
	@Autowired
	private OfferMapper offerMapper;

	@GetMapping({ "/{id}" })
	public ResponseEntity<ServiceResponse<Offer>> getOfferBsdOnID(@PathVariable("id") Long offerID) {
		return ResponseEntity.ok(new ServiceResponseWrapper<Offer>().wrapServiceResponse(
				offerService.getOfferByID(offerID), HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
	}

	@GetMapping({ "/{id}/status" })
	public ResponseEntity<ServiceResponse<Boolean>> offerStatus(@PathVariable("id") Long offerID) {
		return ResponseEntity.ok(new ServiceResponseWrapper<Boolean>().wrapServiceResponse(
				offerService.isActive(offerID), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()));
	}

	@PostMapping
	public ResponseEntity<ServiceResponse<Offer>> saveOffer(@RequestBody OfferDTO offerDTO) {
		Offer offer = offerMapper.dtoToDomain(offerDTO);
		return ResponseEntity.ok(new ServiceResponseWrapper<Offer>().wrapServiceResponse(
				offerService.saveOffer(offer),
				HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));
	}

}
