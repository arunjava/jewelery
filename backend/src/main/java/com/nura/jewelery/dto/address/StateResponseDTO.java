package com.nura.jewelery.dto.address;

import com.nura.jewelery.entity.address.Country;

import lombok.Data;

@Data
public class StateResponseDTO {

	private long id;
	private String stateName;
	private String stateCode;
	private Country country;
	
}
