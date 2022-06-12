package com.nura.jewelery.entity.stock;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nura.jewelery.entity.ProbeClass;
import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "tx_stock_bal", schema = Constants.SCHEMA_JEWEL)
public class StockBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_id")
	private long seq;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name = "batch_code")
	private String batch;
	@Column(name = "bar_code")
	private String barCode;
	@Column(name = "qr_code")
	private String qrCode;
	@Column(name = "stock_bal")
	private double stockBal;
	@ManyToOne
	@JoinColumn(name = "uom_id")
	private UOM uom;
	@Embedded
	private ProbeClass probeClass = new ProbeClass();
}
