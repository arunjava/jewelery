package com.nura.jewelery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.sales.SalesDTO;
import com.nura.jewelery.entity.sales.Sales;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SalesMapper {

	@Mapping(target = "productID", source = "product.productId")
	@Mapping(target = "uomID", source = "uom.id")
	@Mapping(target = "custID", source = "customer.custId")
	@Mapping(target = "productName", source = "product.productName")
	@Mapping(target = "uomDesc", source = "uom.desc")
	public SalesDTO domainTODTO(Sales sales);

	@Mapping(source = "productID", target = "product.productId")
	@Mapping(source = "uomID", target = "uom.id")
	@Mapping(source = "custID", target = "customer.custId")
	public Sales dtoTODomain(SalesDTO salesDTO);

	public List<SalesDTO> domainTODTOs(List<Sales> sales);

}
