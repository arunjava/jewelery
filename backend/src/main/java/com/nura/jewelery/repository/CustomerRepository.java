package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.dto.CustomerDTO;
import com.nura.jewelery.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<CustomerDTO> findByCustId(long id);

}
