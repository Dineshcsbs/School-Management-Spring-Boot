package com.school.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.Admin;
import com.school.management.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PutMapping("/update")
	public Admin updateAdmin(@RequestBody Admin admin,@RequestHeader("Authorization") String token) {
		return adminService.updateAdmin(admin,token.substring(7));
	}
	
}
