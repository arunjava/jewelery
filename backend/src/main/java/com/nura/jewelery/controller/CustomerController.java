package com.nura.jewelery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.CustomerDTO;
import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.service.CustomerService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping(path = "/api/v1/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/customer/{id}")
	public ResponseEntity<ServiceResponse<Customer>> getCustomerDetailsBsdOnId(@PathVariable long id) {

		Customer customer = customerService.getCustomerBsdOnID(id);

		if (customer != null) {
			return ResponseEntity.ok(new ServiceResponseWrapper<Customer>().wrapServiceResponse(customer,
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.OK.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<Customer>().wrapServiceResponse(null,
				HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT.value()));
	}

	@GetMapping(path = "/customer")
	public ResponseEntity<ServiceResponse<List<Customer>>> getAllCustomers() {

		List<Customer> customer = customerService.findAll();

		if (!customer.isEmpty()) {
			return ResponseEntity.ok(new ServiceResponseWrapper<List<Customer>>().wrapServiceResponse(customer,
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.OK.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<List<Customer>>().wrapServiceResponse(null,
				HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT.value()));
	}

	@PostMapping(path = "/customer")
	public ResponseEntity<ServiceResponse<Customer>> saveCustomerDetails(@RequestBody Customer customer) {
		Customer savedCustomer = customerService.save(customer);

		if (savedCustomer != null) {
			return ResponseEntity.ok(new ServiceResponseWrapper<Customer>().wrapServiceResponse(savedCustomer,
					HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<Customer>().wrapServiceResponse(null,
				HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), HttpStatus.EXPECTATION_FAILED.value()));
	}

	@PutMapping(path = "/customer")
	public ResponseEntity<ServiceResponse<Customer>> updateCustomerDetails(@RequestBody Customer customer) {
		Customer savedCustomer = customerService.save(customer);

		if (savedCustomer != null) {
			return ResponseEntity.ok(new ServiceResponseWrapper<Customer>().wrapServiceResponse(savedCustomer,
					HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<Customer>().wrapServiceResponse(null,
				HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), HttpStatus.EXPECTATION_FAILED.value()));
	}

	@PutMapping(path = "/customer/{cust_id}/scheme/{scheme_id}")
	public void updateSchemeForCustomer(@PathVariable(name = "cust_id") long custId,
			@PathVariable(name = "scheme_id") long schemeID) {
		customerService.updateSchemeDtls(custId, schemeID);
	}
}
