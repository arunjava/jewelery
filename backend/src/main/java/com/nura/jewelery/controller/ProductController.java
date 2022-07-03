package com.nura.jewelery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.product.ProductCategoryDTO;
import com.nura.jewelery.dto.product.ProductDTO;
import com.nura.jewelery.dto.product.ProductSubCategoryDTO;
import com.nura.jewelery.mapper.ProductMapper;
import com.nura.jewelery.service.ProductService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductService productService;

	@PostMapping("/category")
	public ResponseEntity<ServiceResponse<ProductCategoryDTO>> saveProductCat(
			@RequestBody ProductCategoryDTO productCatDTO) {

		return ResponseEntity.ok(new ServiceResponseWrapper<ProductCategoryDTO>().wrapServiceResponse(
				productMapper.domainTODTO(productService.saveProductCategory(productMapper.dtoTODomain(productCatDTO))),
				HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));

	}

	@GetMapping("/category")
	public ResponseEntity<ServiceResponse<List<ProductCategoryDTO>>> getProductCategories() {

		List<ProductCategoryDTO> prodCatDTOS = productMapper.domainTOCatDTOs(productService.getProdCats());

		return ResponseEntity.ok(new ServiceResponseWrapper<List<ProductCategoryDTO>>().wrapServiceResponse(prodCatDTOS,
				HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));

	}

	@PostMapping("/sub_category")
	public ResponseEntity<ServiceResponse<ProductSubCategoryDTO>> saveProductSubCat(
			@RequestBody ProductSubCategoryDTO productSubCatDTO) {

		return ResponseEntity.ok(new ServiceResponseWrapper<ProductSubCategoryDTO>().wrapServiceResponse(
				productMapper.domainTODTO(
						productService.saveProductSubCategory(productMapper.dtoTODomain(productSubCatDTO))),
				HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));

	}

	@GetMapping("/sub_category/category/{id}")
	public ResponseEntity<ServiceResponse<List<ProductSubCategoryDTO>>> getProductSubCatByCatID(@PathVariable long id) {
		
		return ResponseEntity.ok(new ServiceResponseWrapper<List<ProductSubCategoryDTO>>().wrapServiceResponse(
				productMapper.domainTOCatSubDTOs(productService.getProdSubCatByCatID(id)), HttpStatus.FOUND.getReasonPhrase(),
				HttpStatus.FOUND.value()));

	}

	@GetMapping("/sub_category/{id}")
	public ResponseEntity<ServiceResponse<ProductSubCategoryDTO>> getProductSubCatByID(@PathVariable long id) {

		return ResponseEntity.ok(new ServiceResponseWrapper<ProductSubCategoryDTO>().wrapServiceResponse(
				productMapper.domainTODTO(productService.getProdSubCatByID(id)), HttpStatus.FOUND.getReasonPhrase(),
				HttpStatus.FOUND.value()));

	}

	@PostMapping
	public ResponseEntity<ServiceResponse<ProductDTO>> saveProductSubCat(@RequestBody ProductDTO productDTO) {

		return ResponseEntity.ok(new ServiceResponseWrapper<ProductDTO>().wrapServiceResponse(
				productMapper.domainTODTO(productService.saveProduct(productMapper.dtoTODomain(productDTO))),
				HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));

	}

	@GetMapping("/{id}")
	public ResponseEntity<ServiceResponse<ProductDTO>> getProductByID(@PathVariable long id) {

		return ResponseEntity.ok(new ServiceResponseWrapper<ProductDTO>().wrapServiceResponse(
				productMapper.domainTODTO(productService.getProductByID(id)), HttpStatus.FOUND.getReasonPhrase(),
				HttpStatus.FOUND.value()));

	}

	@GetMapping("/sub_category/products/{id}")
	public ResponseEntity<ServiceResponse<List<ProductDTO>>> getProductBySubCatID(@PathVariable long id) {
		List<ProductDTO> productDTOs = productMapper.domainsTODTOs(productService.getProductBySubCatID(id));
		return ResponseEntity.ok(new ServiceResponseWrapper<List<ProductDTO>>().wrapServiceResponse(productDTOs,
				HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));

	}
}
