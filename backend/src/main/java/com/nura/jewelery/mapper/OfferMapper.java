package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.offer.OfferDTO;
import com.nura.jewelery.entity.offers.Offer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OfferMapper {

	@Mapping(source = "productID", target = "product.productId")
	Offer dtoToDomain(OfferDTO offerDTO);

	@Mapping(target = "productID", source = "product.productId")
	OfferDTO domainToDTO(Offer offer);

}
