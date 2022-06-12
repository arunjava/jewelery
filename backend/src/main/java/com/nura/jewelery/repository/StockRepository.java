package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.stock.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
