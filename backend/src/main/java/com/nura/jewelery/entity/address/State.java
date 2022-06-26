package com.nura.jewelery.entity.address;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "ms_state", schema = Constants.SCHEMA_JEWEL)
public class State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5416217382583128560L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id")
	private long id;
	@Column(name = "state_name")
	private String stateName;
	@Column(name = "state_code", length = 3)
	private String stateCode;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;
}
