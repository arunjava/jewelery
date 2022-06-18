package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.CustomerDTO;
import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.entity.address.Address;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {

	CustomerDTO domainTODTO(Customer customer);

	Customer dtoTODomain(CustomerDTO customerDTO);
	
}
