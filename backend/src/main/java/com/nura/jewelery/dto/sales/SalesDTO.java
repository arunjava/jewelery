package com.nura.jewelery.dto.sales;

import lombok.Data;

@Data
public class SalesDTO {

	private long txnID;
	private long productID;
	private long uomID;
	private String invoiceNumber;
	private double costPrice;
	private double sellingPrice;
	private double mrp;
	private long custID;
	private double qty;
	private long customerSchemeID;
	private double makingCharges;
	private double wastageCharges;
	private double soldAmt;
	
}
