package com.nura.jewelery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.nura.jewelery.dto.product.ProductCategoryDTO;
import com.nura.jewelery.dto.product.ProductDTO;
import com.nura.jewelery.dto.product.ProductSubCategoryDTO;
import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.entity.product.ProductCategory;
import com.nura.jewelery.entity.product.ProductSubCategory;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

	ProductCategory dtoTODomain(ProductCategoryDTO prodCategoryDTO);

	ProductCategoryDTO domainTODTO(ProductCategory prodCategory);
	
	List<ProductCategoryDTO> domainTOCatDTOs(List<ProductCategory> prodCategory);

	ProductSubCategory dtoTODomain(ProductSubCategoryDTO productSubCategoryDTO);

	ProductSubCategoryDTO domainTODTO(ProductSubCategory productSubCategory);
	
	List<ProductSubCategoryDTO> domainTOCatSubDTOs(List<ProductSubCategory> productSubCategory);

	@Mapping(source = "uomID" , target = "uom.id")
	Product dtoTODomain(ProductDTO productDTO);

	@Mapping(target = "uomID" , source = "uom.id")
	ProductDTO domainTODTO(Product product);
	
	List<ProductDTO> domainsTODTOs(List<Product> product);

}
