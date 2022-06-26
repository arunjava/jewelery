package com.nura.jewelery.dto.address;

import com.nura.jewelery.entity.address.Country;
import com.nura.jewelery.entity.address.District;
import com.nura.jewelery.entity.address.State;
import com.nura.jewelery.entity.address.SubDistrict;

import lombok.Data;

@Data
public class AddressDTO {

	private long addrId;
	private String addr1;
	private String addr2;
	private String addr3;
	private SubDistrict subDistrict;
	private District district;
	private State state;
	private Country country;
	private String landMark;
	private String locality;

}
