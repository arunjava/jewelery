package com.nura.jewelery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.stock.StockInDTO;
import com.nura.jewelery.entity.stock.StockIn;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StockInMapper {

	StockIn dtoToDomain(StockInDTO stockInDTO);

	StockInDTO domainToDTO(StockIn stockIn);
	
	List<StockInDTO> domainToDTOs(List<StockIn>  stockIns);

}
