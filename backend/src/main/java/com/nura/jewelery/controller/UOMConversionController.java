package com.nura.jewelery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.entity.uom.UOMConversion;
import com.nura.jewelery.repository.UOMConversionRepository;

@RestController
@RequestMapping("/api/v1/uom/conversion")
public class UOMConversionController {

	@Autowired
	private UOMConversionRepository uomConversionRepo;

	@PostMapping
	public void saveUOMConversion(@RequestBody UOMConversion uomConversion) {
		uomConversionRepo.save(uomConversion);
	}

}
