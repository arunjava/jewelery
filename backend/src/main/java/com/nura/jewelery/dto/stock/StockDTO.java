package com.nura.jewelery.dto.stock;

import com.nura.jewelery.dto.product.ProductDTO;

import lombok.Data;

@Data
public class StockDTO {

	private long seq;
	private ProductDTO product;
	private String batch;
	private String barCode;
	private String qrCode;

}
