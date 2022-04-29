package com.nura.jewelery.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nura.jewelery.entity.address.Address;
import com.nura.jewelery.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer", schema = Constants.SCHEMA_JEWEL)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Customer implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 487720913687340326L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private long custId;
	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "primary_contact_no")
	private String primaryContactNo;

	@Column(name = "alternate_contact_no")
	private String alterNateContactNo;

	@Column(name = "referral_code")
	private String referralCode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_addr", schema = Constants.SCHEMA_JEWEL, joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "addr_id"))
	private Address address;

	@JsonIgnore
	@Embedded
	private ProbeClass probeClass = new ProbeClass();

}
