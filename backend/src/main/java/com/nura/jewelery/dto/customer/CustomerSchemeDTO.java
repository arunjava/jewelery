package com.nura.jewelery.dto.customer;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerSchemeDTO {

	private long id;
	private long customerID;
	private long schemeID;
	private long uomExchangeID;
	private String uomExchangeVal;
	private Date startDate;
	private Date endDate;

}
