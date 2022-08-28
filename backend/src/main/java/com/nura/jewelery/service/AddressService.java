package com.nura.jewelery.service;

import java.util.List;

import com.nura.jewelery.entity.address.Address;
import com.nura.jewelery.entity.address.Country;
import com.nura.jewelery.entity.address.District;
import com.nura.jewelery.entity.address.State;
import com.nura.jewelery.entity.address.SubDistrict;

public interface AddressService {

	public Address saveAddress(Address address);

	public List<Country> getListOfCountries();

	public List<State> getListOfState(long countryId);

	public List<District> getListOfDistrict(long stateId);

	public List<SubDistrict> getListOfSubDistrict(long districtId);

	public State saveState(State state);

	public District saveDistrict(District district);

	public SubDistrict saveSubDistrict(SubDistrict subDistrict);

}
