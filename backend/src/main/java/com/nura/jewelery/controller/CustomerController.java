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

import com.nura.jewelery.dto.ProductExchangeDTO;
import com.nura.jewelery.dto.customer.CustomerDTO;
import com.nura.jewelery.dto.customer.CustomerSchemeDTO;
import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.mapper.CustomerMapper;
import com.nura.jewelery.service.CustomerService;
import com.nura.jewelery.service.ProductExchangeService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping(path = "/api/v1/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductExchangeService productExchangeService;

	@Autowired
	private CustomerMapper customerMapper;

	@GetMapping(path = "/customer/{id}")
	public ResponseEntity<ServiceResponse<Customer>> getCustomerDetailsBsdOnId(@PathVariable long id) {

		Customer customer = customerService.findByID(id);

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
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<List<Customer>>().wrapServiceResponse(null,
				HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT.value()));
	}

	@GetMapping(path = "/customer/contactNo/{phoneNo}")
	public ResponseEntity<ServiceResponse<CustomerDTO>> getCustomerBsdOnContactNumber(@PathVariable String phoneNo) {
		return ResponseEntity.ok(new ServiceResponseWrapper<CustomerDTO>().wrapServiceResponse(
				customerService.findByContactNumber(phoneNo), HttpStatus.FOUND.getReasonPhrase(),
				HttpStatus.FOUND.value()));
	}

	@PostMapping(path = "/customer")
	public ResponseEntity<ServiceResponse<CustomerDTO>> saveCustomerDetails(@RequestBody CustomerDTO customer) {
		Customer savedCustomer = customerService.save(customerMapper.dtoTODomain(customer));

		if (savedCustomer != null) {
			return ResponseEntity.ok(new ServiceResponseWrapper<CustomerDTO>().wrapServiceResponse(
					customerMapper.domainTODTO(savedCustomer), HttpStatus.CREATED.getReasonPhrase(),
					HttpStatus.CREATED.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<CustomerDTO>().wrapServiceResponse(null,
				HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), HttpStatus.EXPECTATION_FAILED.value()));
	}

	@PutMapping(path = "/customer")
	public ResponseEntity<ServiceResponse<CustomerDTO>> updateCustomerDetails(@RequestBody CustomerDTO customer) {
		Customer savedCustomer = customerService.save(customerMapper.dtoTODomain(customer));

		if (savedCustomer != null) {
			return ResponseEntity.ok(new ServiceResponseWrapper<CustomerDTO>().wrapServiceResponse(
					customerMapper.domainTODTO(savedCustomer), HttpStatus.ACCEPTED.getReasonPhrase(),
					HttpStatus.ACCEPTED.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<CustomerDTO>().wrapServiceResponse(null,
				HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), HttpStatus.EXPECTATION_FAILED.value()));
	}

	@PostMapping(path = "/customer/updateScheme")
	public ResponseEntity<ServiceResponse<String>> updateSchemeForCustomer(
			@RequestBody CustomerSchemeDTO customerSchemeDTO) {
		customerService.updateSchemeDtls(customerSchemeDTO);

		return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse("Customer Scheme update",
				HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));
	}

	@GetMapping(path = "/customer/{cust_id}/scheme")
	public ResponseEntity<ServiceResponse<List<CustomerSchemeDTO>>> getListOfCustomerSchemes(
			@PathVariable(name = "cust_id") long custId) {
		return ResponseEntity.ok(new ServiceResponseWrapper<List<CustomerSchemeDTO>>().wrapServiceResponse(
				customerService.getActiveCustomerSchemeBsdOnCustomerID(custId), HttpStatus.OK.getReasonPhrase(),
				HttpStatus.OK.value()));
	}

	@GetMapping(path = "/customer/{cust_id}/scheme/{prodCatID}")
	public ResponseEntity<ServiceResponse<List<CustomerSchemeDTO>>> getListOfCustomerSchemesBsdOnProdCatID(
			@PathVariable(name = "cust_id") long custId, @PathVariable(name = "prodCatID") long prodCatID) {
		return ResponseEntity.ok(new ServiceResponseWrapper<List<CustomerSchemeDTO>>().wrapServiceResponse(
				customerService.getActiveCustomerSchemeBsdOnCustomerIDAndProdCatID(custId, prodCatID),
				HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
	}

	@GetMapping(path = "/customer/{cust_id}/productExchanges")
	public ResponseEntity<ServiceResponse<List<ProductExchangeDTO>>> getProductExchangeTotal(
			@PathVariable(name = "cust_id") long custId) {
		return ResponseEntity.ok(new ServiceResponseWrapper<List<ProductExchangeDTO>>().wrapServiceResponse(
				productExchangeService.getExchangeTotal(custId), HttpStatus.FOUND.getReasonPhrase(),
				HttpStatus.FOUND.value()));
	}
}
