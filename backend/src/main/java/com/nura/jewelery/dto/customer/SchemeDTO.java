package com.nura.jewelery.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SchemeDTO {

	@JsonProperty("scheme_id")
	private long id;
	@JsonProperty("scheme_name")
	private String schemeName;
	@JsonProperty("scheme_desc")
	private String description;
	@JsonProperty("scheme_active")
	private boolean isActive;

}
