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

import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "ms_product_sub_category", schema = Constants.SCHEMA_JEWEL)
public class ProductSubCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5218377176690266546L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_category_id")
	private long subCategoryId;
	@Column(name = "sub_category_name")
	private String subCategoryName;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private boolean isActive;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private ProductCategory productCategory;
	
}
