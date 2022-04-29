package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.address.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	@Query("Select s From State s where s.country.id = :id")
	public List<State> findStateBsdOnCountryID(Long id);

}
