package com.nura.jewelery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.sales.SalesDTO;
import com.nura.jewelery.entity.sales.Sales;
import com.nura.jewelery.mapper.SalesMapper;
import com.nura.jewelery.service.SalesService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;


@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

	@Autowired
	private SalesMapper salesMapper;

	@Autowired
	private SalesService salesService;

	@PostMapping
	public ResponseEntity<ServiceResponse<SalesDTO>> sales(@RequestBody SalesDTO salesDTO) {
		Sales sale = salesMapper.dtoTODomain(salesDTO);
		return ResponseEntity.ok(new ServiceResponseWrapper<SalesDTO>().wrapServiceResponse(
				salesMapper.domainTODTO(salesService.saveSales(sale)), HttpStatus.CREATED.getReasonPhrase(),
				HttpStatus.CREATED.value()));
	}

}
