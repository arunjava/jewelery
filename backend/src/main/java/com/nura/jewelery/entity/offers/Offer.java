package com.nura.jewelery.entity.offers;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.utils.Constants;

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
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public boolean isActvie() {
		return isActvie;
	}

	public void setActvie(boolean isActvie) {
		this.isActvie = isActvie;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
