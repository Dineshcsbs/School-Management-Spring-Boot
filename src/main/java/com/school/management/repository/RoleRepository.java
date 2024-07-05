package com.school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleType(String role);
	
}
