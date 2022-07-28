package com.nura.jewelery.mapper;

import java.util.List;

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

	List<UomDTO> domaisToDTOS(List<UOM> uoms);

	UOMCategoryDTO domainToDTO(UOMCategory uomCat);

	UOMCategory dtoToDomain(UOMCategoryDTO uomCatDTO);

}
