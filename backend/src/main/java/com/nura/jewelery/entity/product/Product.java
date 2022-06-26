package com.nura.jewelery.entity.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ms_product", schema = Constants.SCHEMA_JEWEL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

	/**
	 * 	
	 */
	private static final long serialVersionUID = 6776805204182880758L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "description")
	private String description;
	@ManyToOne
	@JoinColumn(name = "sub_category_id", referencedColumnName = "sub_category_id")
	private ProductSubCategory productSubCategory;

}
