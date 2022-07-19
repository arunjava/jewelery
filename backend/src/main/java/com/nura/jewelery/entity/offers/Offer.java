package com.nura.jewelery.entity.offers;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "ms_offers", schema = Constants.SCHEMA_JEWEL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2726792539847806260L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private long offerId;
	@Column(name = "is_active")
	private boolean isActvie;
	@Column(name = "offer_desc")
	private String desc;
	@Temporal(TemporalType.DATE)
	@Column(name = "start_dt")
	private Date startDt;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_dt")
	private Date endDt;
	@Column(name = "offer_code")
	private String offerCode;
	@Column(name = "applicable_on")
	private String applicableOn;
	@Column(name = "exp")
	private String expression;

}
