package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.UOMCategoryDTO;
import com.nura.jewelery.entity.uom.UOMCategory;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UOMCategoryMapper {

	UOMCategoryDTO domainToDTO(UOMCategory uomCat);
	
	UOMCategory dtoToDomain(UOMCategoryDTO uoomCatDTO);

}
