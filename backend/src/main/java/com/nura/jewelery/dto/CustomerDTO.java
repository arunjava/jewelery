package com.nura.jewelery.dto;

import java.io.Serializable;
import java.util.Set;

import com.nura.jewelery.entity.Scheme;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@NoArgsConstructor -> don't add as causing failure during projection in repository
@AllArgsConstructor
public class CustomerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6749034648802946711L;

	private long custId;
	private String customerName;
	private String primaryContactNo;
	private String alterNateContactNo;
	private String referralCode;
	
	private Set<Scheme> schemes;

}
