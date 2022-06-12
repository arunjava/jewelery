package com.nura.jewelery.entity.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "tx_stock", schema = Constants.SCHEMA_JEWEL)
@Inheritance(strategy = InheritanceType.JOINED)
public class Stock {

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
//	@Embedded
//	private ProbeClass probeClasse;

}
