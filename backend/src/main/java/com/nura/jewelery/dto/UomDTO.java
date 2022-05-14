package com.nura.jewelery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nura.jewelery.entity.uom.UOMCategory;

import lombok.Data;

@Data
public class UomDTO {

	@JsonProperty("uom_id")
	private long id;
	@JsonProperty("from_uom")
	private String fromUOM;
	@JsonProperty("to_uom")
	private String toUOM;
	@JsonProperty("uom_code")
	private String code;
	@JsonProperty("units")
	private int units;

	@JsonProperty("uom_category")
	private UOMCategoryDTO uomCategory;
	
}
