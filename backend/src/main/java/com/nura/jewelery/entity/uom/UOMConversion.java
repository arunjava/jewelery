package com.nura.jewelery.entity.uom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ms_uom_conversion", schema = Constants.SCHEMA_JEWEL)
public class UOMConversion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2987922321368168315L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "from_uom")
	private String fromUOM;
	@Column(name = "from_uom_id")
	private long fromUOMID;
	@Column(name = "to_uom")
	private String toUOM;
	@Column(name = "to_uom_id")
	private long toUOMID;
	@Column(name = "conversion_val")
	private double conversionVal;

}
