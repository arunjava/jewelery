package com.nura.jewelery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nura.jewelery.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
