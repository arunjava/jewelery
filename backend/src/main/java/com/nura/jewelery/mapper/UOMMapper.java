package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.UOMCategoryDTO;
import com.nura.jewelery.dto.UomDTO;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.entity.uom.UOMCategory;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UOMMapper {

	UOM dtoToDomain(UomDTO uomDTO);

	UomDTO domainToDTO(UOM uom);

	UOMCategoryDTO domainToDTO(UOMCategory uomCat);

	UOMCategory dtoToDomain(UOMCategoryDTO uoomCatDTO);

}
