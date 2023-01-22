package com.nura.jewelery.entity.sales;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nura.jewelery.entity.product.ProductCategory;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ms_gst", schema = Constants.SCHEMA_JEWEL)
public class GST implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092425764076271760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gst_id")
	private long gstID;

	@Column(name = "cgst_percent")
	private int cgstPercentage;
	@Column(name = "sgst_percent")
	private int sgstPercentage;
	@Column(name = "gst_percent")
	private int gstPercentage;

	@Column(name = "prod_cat_id")
	private ProductCategory productCategory;

}
