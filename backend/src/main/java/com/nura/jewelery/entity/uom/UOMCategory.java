package com.nura.jewelery.entity.uom;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nura.jewelery.utils.Constants;

import lombok.Data;

/**
 * 
 * @author pranav
 *
 *UOM Category should fall into below list
 *1) Quantity
 *2) Weight
 *3) Time
 *4) Volume
 *
 */

@Data
@Entity
@Table(name = "ms_uom_category", schema = Constants.SCHEMA_JEWEL)
public class UOMCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8487439083638238503L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uom_cat_id")
	private long id;
	@Column(name = "uom_category")
	private String uomCat;

	@OneToMany
	private Set<UOM> uom;

}
