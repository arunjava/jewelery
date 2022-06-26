package com.nura.jewelery.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nura.jewelery.entity.address.Address;
import com.nura.jewelery.entity.sales.Sales;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ms_customer", schema = Constants.SCHEMA_JEWEL)
@Getter @Setter
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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "mp_customer_addr", schema = Constants.SCHEMA_JEWEL, joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "addr_id"))
	private Address address;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "mp_customer_scheme", schema = Constants.SCHEMA_JEWEL, joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "scheme_id"))
	@JsonIgnoreProperties("customers")
	private Set<Scheme> schemes = new HashSet<>();
	
	@JsonIgnore
	@Embedded
	private ProbeClass probeClass = new ProbeClass();
	
//	@OneToMany
//	@JoinColumn(name = "customer_id", referencedColumnName = "cust_id")
//	private List<Sales> sales;

}
