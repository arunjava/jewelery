package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.product.PricingDTO;
import com.nura.jewelery.entity.product.Pricing;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PricingMapper {

	@Mapping(source = "productID", target = "product.productId")
	@Mapping(source = "uomID", target = "uom.id")
	Pricing dtoTODomain(PricingDTO pricingDTO);

	@Mapping(target = "productID", source = "product.productId")
	@Mapping(target = "uomID", source = "uom.id")
	PricingDTO domainTODTO(Pricing pricing);
}
