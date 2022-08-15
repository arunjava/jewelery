package com.nura.jewelery.dto.customer;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nura.jewelery.dto.offer.OfferDTO;
import com.nura.jewelery.entity.product.ProductCategory;

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
	private Date beginDate;
	private Date endDate;
	private long uomID;
	private int duration;
	private List<OfferDTO> offers;
	private ProductCategory prodCategory;
	
}
