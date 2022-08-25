package com.nura.jewelery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductExchangeDTO {

	private double exchangeVal;
	private long productID;
	private long customerID;
	private String productName;
	private long uomID;
	private String uomDesc;

}
