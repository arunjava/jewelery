package com.nura.jewelery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.SchemeDTO;
import com.nura.jewelery.entity.Scheme;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SchemeMapper {

	Scheme dtoToDomain(SchemeDTO schemeDTO);
	
	SchemeDTO domainToDTO(Scheme scheme);

	List<Scheme> dtoToDomain(List<SchemeDTO> schemeDTO);
	
	List<SchemeDTO> domainToDTO(List<Scheme> scheme);
}
