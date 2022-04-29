package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.address.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

	@Query("FROM District d where d.state.id = :id")
	public List<District> getDistrictBsdOnSateId(long id);

}
