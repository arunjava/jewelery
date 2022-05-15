package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
