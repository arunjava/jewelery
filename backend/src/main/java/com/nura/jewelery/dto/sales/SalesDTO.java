package com.nura.jewelery.dto.sales;

import java.util.Date;

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
	private double profitAmt;
	private Date txnDate;
	private long custID;
	private double qty;
	
}
