package com.nura.jewelery.dto.product;

import lombok.Data;

@Data
public class ProductCategoryDTO {

	private long categoryId;
	private String categoryName;
	private String description;
	private boolean categoryStatus;
	
}
