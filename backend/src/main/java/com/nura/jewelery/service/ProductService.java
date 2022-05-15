package com.nura.jewelery.service;

import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.entity.product.ProductCategory;
import com.nura.jewelery.entity.product.ProductSubCategory;

public interface ProductService {

	public ProductCategory saveProductCategory(ProductCategory productCategory);

	public ProductCategory getProdCatByID(long productCatID);

	public ProductSubCategory saveProductSubCategory(ProductSubCategory productCategory);

	public ProductSubCategory getProdSubCatByID(long productSubCatID);

	public Product saveProduct(Product product);

	public Product getProductByID(long productID);

}
