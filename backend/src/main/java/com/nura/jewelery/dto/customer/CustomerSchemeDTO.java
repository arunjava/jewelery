package com.nura.jewelery.dto.customer;

import java.util.Date;

import com.nura.jewelery.entity.scheme.Scheme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSchemeDTO {

	private long id;
	private long customerID;
	private long schemeID;
	private long uomExchangeID;
	private String uomExchangeVal;
	private Date startDate;
	private Date endDate;
	private SchemeDTO scheme;

	public CustomerSchemeDTO(long id, long customerID, long schemeID, long uomExchangeID, String uomExchangeVal,
			Date startDate, Date endDate) {
		this.id = id;
		this.customerID = customerID;
		this.schemeID = schemeID;
		this.uomExchangeID = uomExchangeID;
		this.uomExchangeVal = uomExchangeVal;
		this.startDate = startDate;
		this.endDate = endDate;

	}

}
