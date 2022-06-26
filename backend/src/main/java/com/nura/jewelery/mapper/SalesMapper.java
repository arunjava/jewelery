package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.sales.SalesDTO;
import com.nura.jewelery.entity.sales.Sales;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SalesMapper {


	public SalesDTO domainTODTO(Sales sales);

	@Mapping(source = "productID", target = "product.productId")
	@Mapping(source = "uomID", target = "uom.id")
	@Mapping(source = "custID", target = "customer.custId")
	public Sales dtoTODomain(SalesDTO salesDTO);
	
}
