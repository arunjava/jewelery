package com.nura.jewelery.service;

import java.util.List;

import com.nura.jewelery.dto.customer.CustomerDTO;
import com.nura.jewelery.dto.customer.CustomerSchemeDTO;
import com.nura.jewelery.entity.Customer;

public interface CustomerService {

	public Customer save(Customer customer);
	
	public Customer findByID(long custId);
	
	public CustomerDTO findByContactNumber(String contactNumber);

	public List<CustomerDTO> get(long custId);

	public Customer getCustomerBsdOnID(long custId);

	public List<Customer> findAll();

	public void updateSchemeDtls(CustomerSchemeDTO cusSchemeDTO);
	
	public List<CustomerSchemeDTO> getActiveCustomerSchemeBsdOnCustomerID(long customerID);
}
