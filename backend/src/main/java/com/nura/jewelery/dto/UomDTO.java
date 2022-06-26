package com.nura.jewelery.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UomDTO {

	@JsonProperty("uom_id")
	private long id;
	@JsonProperty("uom_code")
	private String code;
	@Column(name = "uom_desc")
	private String desc;
	@JsonProperty("uom_category")
	private UOMCategoryDTO uomCategory;
	
}
