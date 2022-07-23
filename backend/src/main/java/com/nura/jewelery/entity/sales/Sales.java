package com.nura.jewelery.entity.sales;

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

import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tx_sales", schema = Constants.SCHEMA_JEWEL)
public class Sales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092425764076271760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "txn_id")
	private long txnID;
	@ManyToOne(optional = false)
	@JoinColumn(name = "prod_id")
	private Product product;
	@ManyToOne(optional = false)
	@JoinColumn(name = "uom_id")
	private UOM uom;
	@Column(name = "inv_no")
	private String invoiceNumber;
	@Column(name = "cp_amt")
	private double costPrice;
	@Column(name = "sp_amt")
	private double sellingPrice;
	@Column(name = "mrp_amt")
	private double mrp;
	@Column(name = "sold_amt")
	private double soldAmt;
	@Column(name = "profit_amt")
	private double profitAmt;
	@Column(name = "qty")
	private double qty;
	@Column(name = "txn_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", referencedColumnName = "cust_id")
	private Customer customer;
	@Column(name = "making_charges")
	private double makingCharges;
	@Column(name = "wastage_charges")
	private double wastageCharges;

	@PrePersist
	public void setTxnDate() {
		txnDate = new Date();
	}
}
