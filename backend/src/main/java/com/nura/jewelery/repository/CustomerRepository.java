package com.nura.jewelery.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.dto.CustomerDTO;
import com.nura.jewelery.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<CustomerDTO> findByCustId(long id);

}
