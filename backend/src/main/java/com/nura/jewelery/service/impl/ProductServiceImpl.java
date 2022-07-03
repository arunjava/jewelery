package com.nura.jewelery.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.entity.product.ProductCategory;
import com.nura.jewelery.entity.product.ProductSubCategory;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.ProductCategoryRepository;
import com.nura.jewelery.repository.ProductRepository;
import com.nura.jewelery.repository.ProductSubCategoryRepository;
import com.nura.jewelery.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductCategoryRepository prodCatRepo;

	@Autowired
	private ProductSubCategoryRepository prodSubCatRepo;

	@Override
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		return prodCatRepo.save(productCategory);
	}

	@Override
	public ProductCategory getProdCatByID(long productCatID) {
		Optional<ProductCategory> prodCat = prodCatRepo.findById(productCatID);
		if (prodCat.isEmpty()) {
			throw new NotFoundException("Product category not found for id " + productCatID);
		}

		return prodCat.get();
	}

	@Override
	public ProductSubCategory saveProductSubCategory(ProductSubCategory prodSubCategory) {
		return prodSubCatRepo.save(prodSubCategory);
	}

	@Override
	public ProductSubCategory getProdSubCatByID(long productSubCatID) {
		Optional<ProductSubCategory> prodsubCat = prodSubCatRepo.findById(productSubCatID);
		if (prodsubCat.isEmpty()) {
			throw new NotFoundException("Product sub category not found for id " + productSubCatID);
		}

		return prodsubCat.get();
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product getProductByID(long productID) {
		Optional<Product> product = productRepo.findById(productID);
		if (product.isEmpty()) {
			throw new NotFoundException("Product not found for id " + productID);
		}

		return product.get();
	}

	@Override
	public List<ProductSubCategory> getProdSubCatByCatID(long productCatID) {
		return prodSubCatRepo.findByCategoryID(productCatID);
	}

	@Override
	public List<ProductCategory> getProdCats() {
		return prodCatRepo.findAll();
	}

	@Override
	public List<Product> getProductBySubCatID(long subCatID) {
		return productRepo.getProductBsdOnSubCatID(subCatID);
	}

}
