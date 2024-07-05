package com.school.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.Role;
import com.school.management.exception.BadRequestServiceAlertException;
import com.school.management.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role createRole(final Role role) {
		return this.roleRepository.save(role);
	}
	
	public String deleteRole(final String role) {
		Role roleValue;
		try{
			roleValue= roleRepository.findByRoleType(role);
		}
		catch(Exception e) {
			throw new BadRequestServiceAlertException(404,"Role not found :"+role);
		}
	    roleRepository.deleteById(roleValue.getId());
	    return "Role has been deleted: " + roleValue.getId();
	}

}
