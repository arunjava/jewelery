package com.nura.jewelery.entity.sales;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "tx_sales",  schema = Constants.SCHEMA_JEWEL)
public class Sales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092425764076271760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_id")
	private long seq;

	@ManyToOne
	@JoinColumn(name = "prod_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "uom_id")
	private UOM uom;

	@Column(name = "inv_no")
	private String invoiceNumber;

	@Column(name = "sold_amt")
	private double soldAmount;

}
