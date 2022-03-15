package com.nura.textiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nura.textiles.entity.user.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
