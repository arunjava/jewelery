package com.nura.jewelery.dto.product;

import lombok.Data;

@Data
public class ProductSubCategoryDTO {

	private long subCategoryId;
	private String subCategoryName;
	private String description;
	private boolean isActive;
	private ProductCategoryDTO productCategory;
	
}
