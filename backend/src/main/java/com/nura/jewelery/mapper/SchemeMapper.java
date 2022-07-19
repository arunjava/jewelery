package com.nura.jewelery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.customer.SchemeDTO;
import com.nura.jewelery.dto.offer.OfferDTO;
import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.entity.scheme.Scheme;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SchemeMapper {

	@Mapping(source =  "uomID", target = "uom.id")
	Scheme dtoToDomain(SchemeDTO schemeDTO);
	
	@Mapping(source = "uom.id", target = "uomID")
	SchemeDTO domainToDTO(Scheme scheme);
	
	OfferDTO domainToOfferDTO(Offer offer);
	
	List<OfferDTO> domainToOfferDTOs(List<Offer> offer);
	
	Offer dtoToOfferDomain(OfferDTO offerDTO);

	List<Scheme> dtoToDomain(List<SchemeDTO> schemeDTO);
	
	List<SchemeDTO> domainToDTO(List<Scheme> scheme);

}
