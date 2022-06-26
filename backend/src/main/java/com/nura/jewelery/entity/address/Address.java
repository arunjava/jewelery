package com.nura.jewelery.entity.address;

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

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "addrs_dtls", schema = Constants.SCHEMA_JEWEL)
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4101590684528126888L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addr_id")
	private long addrId;
	@Column(name = "addr_1")
	private String addr1;
	@Column(name = "addr_2")
	private String addr2;
	@Column(name = "addr_3")
	private String addr3;

	@ManyToOne
	@JoinColumn(name = "sub_district_id")
	private SubDistrict subDistrict;

	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@Column(name = "pin_code", length = 6)
	private String pincode;
	@Column(name = "land_mark")
	private String landMark;
	
	@Column(name = "locality")
	private String locality;

	@JsonIgnore
	@Embedded
	private ProbeClass probeClass = new ProbeClass();

}
