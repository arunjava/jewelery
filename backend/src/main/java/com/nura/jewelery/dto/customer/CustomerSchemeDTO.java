package com.nura.jewelery.dto.customer;

import lombok.Data;

@Data
public class CustomerSchemeDTO {

	private long customerID;
	private long schemeID;
	private long uomExchangeID;
	private String uomExchangeVal;

}
