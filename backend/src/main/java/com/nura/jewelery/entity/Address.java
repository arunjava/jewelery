package com.nura.jewelery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
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
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name = "pin_code")
	private String pincode;
	@Column(name = "land_mark")
	private String landMark;
	@Column(name = "locality")
	private String locality;

	@Embedded
	private ProbeClass probeClass;

//	@ManyToOne
//	private User user;

}
