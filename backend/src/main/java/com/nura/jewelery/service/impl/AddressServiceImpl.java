package com.nura.jewelery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.address.Address;
import com.nura.jewelery.entity.address.Country;
import com.nura.jewelery.entity.address.District;
import com.nura.jewelery.entity.address.State;
import com.nura.jewelery.entity.address.SubDistrict;
import com.nura.jewelery.repository.AddressRepository;
import com.nura.jewelery.repository.CountryRepository;
import com.nura.jewelery.repository.DistrictRepository;
import com.nura.jewelery.repository.StateRepository;
import com.nura.jewelery.repository.SubDistrictRepository;
import com.nura.jewelery.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private SubDistrictRepository subDistrictRepository;

	public List<Country> getListOfCountries() {
		return countryRepository.findAll();
	}

	@Override
	public List<State> getListOfState(long countryId) {
		return stateRepository.findStateBsdOnCountryID(countryId);
	}

	@Override
	public List<District> getListOfDistrict(long stateId) {
		return districtRepository.getDistrictBsdOnSateId(stateId);
	}

	@Override
	public List<SubDistrict> getListOfSubDistrict(long districtId) {
		return subDistrictRepository.getSubDistrictBsdOnDistrictId(districtId);
	}

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

}
