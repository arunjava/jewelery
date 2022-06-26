package com.nura.jewelery.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.dto.CustomerDTO;
import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.entity.Scheme;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.CustomerRepository;
import com.nura.jewelery.service.CustomerService;
import com.nura.jewelery.service.SchemeService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SchemeService schemeService;

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<CustomerDTO> get(long custId) {
		return customerRepository.findByCustId(custId);
	}

	public Customer getCustomerBsdOnID(long custId) {
		Optional<Customer> customer = customerRepository.findById(custId);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new NotFoundException("Customer not found for id :" + custId);
		}
	}
	
	public Customer findByID(long custId) {
		Optional<Customer> customer = customerRepository.findById(custId);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new NotFoundException("Customer not found for id :" + custId);
		}
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public void updateSchemeDtls(long custId, long schemeId) {
		Optional<Customer> customer = customerRepository.findById(custId);
		if (customer.isPresent()) {
			Optional<Scheme> scheme = schemeService.getSchemeBsdOnID(schemeId);
			if (scheme.isPresent()) {
				Set<Scheme> schemes = customer.get().getSchemes();
				schemes.add(scheme.get());
				customer.get().setSchemes(schemes);

				customerRepository.save(customer.get());

			} else {
				throw new NotFoundException("Scheme not found for id " + schemeId);
			}
		} else {
			throw new NotFoundException("Customer not found for id " + custId);
		}
	}
}
