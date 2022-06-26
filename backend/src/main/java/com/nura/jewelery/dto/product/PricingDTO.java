package com.nura.jewelery.dto.product;

import java.util.Date;

import lombok.Data;

@Data
public class PricingDTO {

	private long id;
	private double costPrice;
	private double salesPrice;
	private double mrp;
	private boolean isActive;
	private Date crtdDt;
	private long productID;
	private long uomID;

}
