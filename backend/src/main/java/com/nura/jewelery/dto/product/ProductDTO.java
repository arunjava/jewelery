package com.nura.jewelery.dto.product;

import lombok.Data;

@Data
public class ProductDTO {

	private long productId;
	private String productName;
	private String description;
	private ProductCategoryDTO productCategory;
	private ProductSubCategoryDTO productSubCategory;

}
