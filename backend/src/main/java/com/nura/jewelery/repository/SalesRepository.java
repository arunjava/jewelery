package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.sales.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

}
