package com.nura.jewelery.dto.offer;

import java.util.Date;

import lombok.Data;

@Data
public class OfferDTO {

	private long offerId;
	private boolean isActvie;
	private String desc;
	private Date startDt;
	private Date endDt;
	private long productID;

}
