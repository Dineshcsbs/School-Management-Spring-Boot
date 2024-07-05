package com.school.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.Admin;
import com.school.management.exception.BadRequestServiceAlertException;
import com.school.management.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private JwtService jwtService;

	public Admin updateAdmin(Admin admin,String token) {
		if(!jwtService.extractUsername(token).equals(admin.getEmail())) {
			throw new BadRequestServiceAlertException(400,"Email or token invalid");
		}
		
		Optional<Admin> adminDetail=adminRepository.findByEmail(admin.getEmail());
		if(adminDetail.isEmpty()) {
			throw new BadRequestServiceAlertException(400,"Email is not invalid");
		}
		
		Admin adminInfo=adminDetail.get();
		
		if(admin.getEmail()!=null) {
			adminInfo.setAddres(admin.getEmail());
		}
		if(admin.getName()!=null) {
			adminInfo.setName(admin.getName());
		}
		return adminRepository.save(adminInfo);
		
	}
	
}
