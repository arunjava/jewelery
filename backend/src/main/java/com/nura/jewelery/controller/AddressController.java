package com.nura.jewelery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.entity.address.Address;
import com.nura.jewelery.entity.address.Country;
import com.nura.jewelery.entity.address.District;
import com.nura.jewelery.entity.address.State;
import com.nura.jewelery.entity.address.SubDistrict;
import com.nura.jewelery.service.AddressService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping(path = "/address/countries")
	public ResponseEntity<ServiceResponse<List<Country>>> getListOfCountries() {
		List<Country> countries = addressService.getListOfCountries();

		if (!countries.isEmpty()) {
			return ResponseEntity.ok(new ServiceResponseWrapper<List<Country>>().wrapServiceResponse(countries,
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<List<Country>>().wrapServiceResponse(null,
				HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
	}

	@GetMapping(path = "/address/states/{countryID}")
	public ResponseEntity<ServiceResponse<List<State>>> getListOfStatesBsdOnCountryID(
			@PathVariable("countryID") long countryID) {
		List<State> states = addressService.getListOfState(countryID);
		if (!states.isEmpty()) {
			return ResponseEntity.ok(new ServiceResponseWrapper<List<State>>().wrapServiceResponse(states,
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<List<State>>().wrapServiceResponse(null,
				HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
	}

	@GetMapping(path = "/address/district/{stateID}")
	public ResponseEntity<ServiceResponse<List<District>>> getListOfDistrictBsdOnSateID(
			@PathVariable("stateID") long stateID) {
		List<District> districts = addressService.getListOfDistrict(stateID);
		if (!districts.isEmpty()) {
			return ResponseEntity.ok(new ServiceResponseWrapper<List<District>>().wrapServiceResponse(districts,
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<List<District>>().wrapServiceResponse(null,
				HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
	}

	@GetMapping(path = "/address/subDistrict/{districtID}")
	public ResponseEntity<ServiceResponse<List<SubDistrict>>> getListOfSubDistrictBsdOnDistrictID(
			@PathVariable("districtID") long districtID) {
		List<SubDistrict> subDistricts = addressService.getListOfSubDistrict(districtID);
		if (!subDistricts.isEmpty()) {
			return ResponseEntity.ok(new ServiceResponseWrapper<List<SubDistrict>>().wrapServiceResponse(subDistricts,
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<List<SubDistrict>>().wrapServiceResponse(null,
				HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
	}

	@PostMapping(path = "/address/save")
	public ResponseEntity<ServiceResponse<Address>> saveAddress(@RequestBody Address address) {
		return ResponseEntity.ok(new ServiceResponseWrapper<Address>().wrapServiceResponse(
				addressService.saveAddress(address), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()));
	}
}
