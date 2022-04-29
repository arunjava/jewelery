package com.nura.jewelery.entity.address;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nura.jewelery.utils.Constants;

import lombok.Data;

/**
 * 
 * @author pranav
 *
 *         City/District details master data
 */

@Data
@Entity
@Table(name = "ms_district", schema = Constants.SCHEMA_JEWEL)
public class District implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300801907561403650L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "district_id")
	private long id;
	@Column(name = "district_name")
	private String districtName;
	@Column(name = "district_code", length = 3)
	private String districtCode;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

}
