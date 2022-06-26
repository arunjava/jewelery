package com.nura.jewelery.service;

import java.util.List;

import com.nura.jewelery.dto.CustomerDTO;
import com.nura.jewelery.entity.Customer;

public interface CustomerService {

	public Customer save(Customer customer);
	
	public Customer findByID(long custId);

	public List<CustomerDTO> get(long custId);

	public Customer getCustomerBsdOnID(long custId);

	public List<Customer> findAll();

	public void updateSchemeDtls(long custId, long schemeId);
}
