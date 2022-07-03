package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.product.ProductSubCategory;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {
	
	@Query("Select subcat from ProductSubCategory subcat where productCategory.categoryId =:categoryId")
	public List<ProductSubCategory> findByCategoryID(long categoryId);

}
