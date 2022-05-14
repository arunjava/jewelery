package com.nura.jewelery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UOMCategoryDTO {

	@JsonProperty("uom_cat_id")
	private long id;
	@JsonProperty("uom_cat_desc")
	private String uomCat;
}
