package com.nura.jewelery.mapper;

import org.mapstruct.Mapper;
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

	ProductSubCategory dtoTODomain(ProductSubCategoryDTO productSubCategoryDTO);

	ProductSubCategoryDTO domainTODTO(ProductSubCategory productSubCategory);

	Product dtoTODomain(ProductDTO productDTO);

	ProductDTO domainTODTO(Product product);

}
