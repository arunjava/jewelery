package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.offer.OfferDTO;
import com.nura.jewelery.entity.offers.Offer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OfferMapper {

	Offer dtoToDomain(OfferDTO offerDTO);

	OfferDTO domainToDTO(Offer offer);

}
