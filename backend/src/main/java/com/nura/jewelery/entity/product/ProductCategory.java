package com.nura.jewelery.entity.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "ms_product_category" , schema = Constants.SCHEMA_JEWEL)
public class ProductCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5403684689633427387L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private long categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@Column(name = "description")
	private String description;
	@Column(name = "category_status")
	private boolean categoryStatus;
}
