package com.nura.jewelery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.dto.CustomerDTO;
import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	/*
	 * public List<CustomerDTO> get(long custId) { return
	 * customerRepository.findByCustId(custId); }
	 */
}
