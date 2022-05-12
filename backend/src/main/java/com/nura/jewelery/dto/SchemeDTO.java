package com.nura.jewelery.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SchemeDTO {

	private long id;
	private String schemeName;
	private String description;
	private boolean isActive;
	private Date beginDate;
	private Date endDate;
	

}
