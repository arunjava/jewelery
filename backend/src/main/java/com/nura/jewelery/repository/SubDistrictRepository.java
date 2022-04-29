package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.address.SubDistrict;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {

	@Query("FROM SubDistrict s where s.district.id = :id")
	public List<SubDistrict> getSubDistrictBsdOnDistrictId(long id);

}
