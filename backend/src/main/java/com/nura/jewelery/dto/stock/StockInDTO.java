package com.nura.jewelery.dto.stock;

import com.nura.jewelery.dto.UomDTO;

import lombok.Data;

@Data
public class StockInDTO extends StockDTO{

	private String barCode;
	private String qrCode;
	private long qty;
	private Double costPrice;
	private Double mrp;
	private Double discount;
	private String batch;
	private UomDTO uom;
}
