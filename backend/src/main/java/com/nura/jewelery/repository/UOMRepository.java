package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.uom.UOM;

@Repository
public interface UOMRepository extends JpaRepository<UOM, Long> {

}
