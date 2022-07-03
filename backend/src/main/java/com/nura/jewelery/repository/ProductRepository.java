package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select prod from Product prod where prod.productSubCategory.subCategoryId =:subCategoryId")
	public List<Product> getProductBsdOnSubCatID(long subCategoryId);

}
