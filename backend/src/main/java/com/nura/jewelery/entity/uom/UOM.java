package com.nura.jewelery.entity.uom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nura.jewelery.entity.ProbeClass;
import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
//@NoArgsConstructor
@Entity
@Table(name = "ms_uom", schema = Constants.SCHEMA_JEWEL)
public class UOM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8594659489956013613L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uom_id")
	private Long id;
	@Column(name = "from_uom")
	private String fromUOM;
	@Column(name = "to_uom")
	private String toUOM;
	@Column(name = "uom_code")
	private String code;
	@Column(name = "units")
	private int units;

	@JsonIgnore
	@Embedded
	ProbeClass probeClass = new ProbeClass();

	@ManyToOne
	@JoinColumn(name = "uom_cat_id")
	private UOMCategory uomCategory;

}
