package com.nura.jewelery.entity.product;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ms_pricing", schema = Constants.SCHEMA_JEWEL)
public class Pricing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7520475481560827698L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private long id;
	@Column(name = "cost_price")
	private double costPrice;
	@Column(name = "sales_price")
	private double salesPrice;
	@Column(name = "mrp")
	private double mrp;
	@Column(name = "is_active")
	private boolean isActive;
	@Column(name = "crtd_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date crtdDt;
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	@ManyToOne
	@JoinColumn(name = "uom_id", nullable = false)
	private UOM uom;

	@PrePersist
	public void setCrtdDate() {
		this.crtdDt = new Date();
	}
}
