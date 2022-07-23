package com.nura.jewelery.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.dto.customer.CustomerDTO;
import com.nura.jewelery.dto.customer.CustomerSchemeDTO;
import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.entity.scheme.CustomerScheme;
import com.nura.jewelery.entity.scheme.Scheme;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.CustomerRepository;
import com.nura.jewelery.repository.CustomerSchemeRepository;
import com.nura.jewelery.repository.UOMRepository;
import com.nura.jewelery.service.CustomerService;
import com.nura.jewelery.service.SchemeService;
import com.nura.jewelery.utils.DateUtils;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerSchemeRepository customerSchemeRepository;

	@Autowired
	private UOMRepository uomRepository;

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

	public void updateSchemeDtls(CustomerSchemeDTO cusSchemeDTO) {
		Optional<Customer> customer = customerRepository.findById(cusSchemeDTO.getCustomerID());
		if (customer.isPresent()) {
			Optional<Scheme> scheme = schemeService.getSchemeBsdOnID(cusSchemeDTO.getSchemeID());
			if (scheme.isPresent()) {
				Date startDate = Calendar.getInstance().getTime();
				CustomerScheme customerScheme = new CustomerScheme();
				customerScheme.setCustomer(customer.get());
				customerScheme.setScheme(scheme.get());
				customerScheme.setStartDate(startDate);
				customerScheme.setEndDate(DateUtils.addDaysBsdOnCategory(startDate, scheme.get().getDuration(),
						scheme.get().getUom().getCode()));
				customerScheme.setUom(scheme.get().getUom());
				customerScheme.setUomVal("" + scheme.get().getDuration());
				Optional<UOM> exchangeUOM = uomRepository.findById(cusSchemeDTO.getUomExchangeID());
				if (exchangeUOM.isPresent()) {
					customerScheme.setExchangeUOM(exchangeUOM.get());
				} else {
					throw new NotFoundException("Exchange UOM not found for id " + cusSchemeDTO.getUomExchangeID());
				}
				customerScheme.setExchangeVal(cusSchemeDTO.getUomExchangeVal());
				customerSchemeRepository.save(customerScheme);
			} else {
				throw new NotFoundException("Scheme not found for id " + cusSchemeDTO.getSchemeID());
			}
		} else {
			throw new NotFoundException("Customer not found for id " + cusSchemeDTO.getCustomerID());
		}
	}

	@Override
	public CustomerDTO findByContactNumber(String contactNumber) {
		CustomerDTO customer = customerRepository.findByPrimaryContactNo(contactNumber);
		if (customer != null) {
			return customer;
		}

		throw new NotFoundException("Customer not found for contact number :" + contactNumber);
	}

	@Override
	public List<CustomerSchemeDTO> getActiveCustomerSchemeBsdOnCustomerID(long customerID) {
		return customerSchemeRepository.getActiveCustomerSchemeBsdOnID(customerID);
	}
}
