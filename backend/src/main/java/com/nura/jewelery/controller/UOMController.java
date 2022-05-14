package com.nura.jewelery.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.UomDTO;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.mapper.UOMMapper;
import com.nura.jewelery.service.UOMService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UOMController {

	@Autowired
	private UOMService uomService;

	@Autowired
	private UOMMapper uomMapper;

	@PostMapping("/uom")
	public ResponseEntity<ServiceResponse<UOM>> saveUOM(@RequestBody UomDTO uom) {
		return ResponseEntity
				.ok(new ServiceResponseWrapper<UOM>().wrapServiceResponse(uomService.save(uomMapper.dtoToDomain(uom)),
						HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));
	}

	@GetMapping("/{uom_id}")
	public ResponseEntity<ServiceResponse<UomDTO>> findUOMByID(@PathVariable("uom_id") long uomID) {
		return ResponseEntity.ok(new ServiceResponseWrapper<UomDTO>().wrapServiceResponse(
				uomMapper.domainToDTO(uomService.findByID(uomID)), HttpStatus.CREATED.getReasonPhrase(),
				HttpStatus.CREATED.value()));
	}
}
