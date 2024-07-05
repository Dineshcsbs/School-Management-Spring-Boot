package com.school.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.Role;
import com.school.management.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("/")
	
	public Role createRole(@RequestBody Role role) {
		return this.roleService.createRole(role);
	}
	
	@DeleteMapping("/")
	public String deleteRole(@RequestParam String role) {
		return this.roleService.deleteRole(role);
	}
	
}
