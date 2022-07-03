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

import com.nura.jewelery.dto.product.PricingDTO;
import com.nura.jewelery.entity.product.Pricing;
import com.nura.jewelery.mapper.PricingMapper;
import com.nura.jewelery.service.PricingService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping("/api/v1")
public class PricingController {

	@Autowired
	private PricingMapper pricingMapper;

	@Autowired
	private PricingService pricingService;

	@PostMapping("/pricing")
	public ResponseEntity<ServiceResponse<PricingDTO>> savePricing(@RequestBody PricingDTO pricingDTO) {
		Pricing pricing = pricingMapper.dtoTODomain(pricingDTO);
		return ResponseEntity.ok(new ServiceResponseWrapper<PricingDTO>().wrapServiceResponse(
				pricingMapper.domainTODTO(pricingService.save(pricing)), HttpStatus.CREATED.getReasonPhrase(),
				HttpStatus.CREATED.value()));
	}
	
	@GetMapping("/pricing/{prodID}/{uomID}")
	public ResponseEntity<ServiceResponse<PricingDTO>> getPricingBsdOnProdIDNUOMID(@PathVariable long prodID, @PathVariable long uomID) {
		return ResponseEntity.ok(new ServiceResponseWrapper<PricingDTO>().wrapServiceResponse(
				pricingMapper.domainTODTO(pricingService.getPricingBsdOnProdNUomID(prodID, uomID)), HttpStatus.FOUND.getReasonPhrase(),
				HttpStatus.FOUND.value()));
	}

}
