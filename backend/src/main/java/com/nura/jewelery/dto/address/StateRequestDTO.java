package com.nura.jewelery.dto.address;

import com.nura.jewelery.entity.address.Country;

import lombok.Data;

@Data
public class StateRequestDTO {

	private String stateName;
	private String stateCode;
	private Country country;

}
