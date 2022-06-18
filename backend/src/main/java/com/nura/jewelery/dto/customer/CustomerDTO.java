package com.nura.jewelery.dto.customer;

import java.util.HashSet;
import java.util.Set;

import com.nura.jewelery.entity.Scheme;
import com.nura.jewelery.entity.address.Address;

import lombok.Data;

@Data
public class CustomerDTO {

	private long custId;
	private String customerName;
	private String primaryContactNo;
	private String alterNateContactNo;
	private String referralCode;
	private Address address;
	private Set<Scheme> schemes;

}
