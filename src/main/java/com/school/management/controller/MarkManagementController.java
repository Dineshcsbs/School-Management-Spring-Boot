package com.school.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.MarkManagement;
import com.school.management.service.MarkManagementService;


@RestController
@RequestMapping("/api/mark")
public class MarkManagementController {

	@Autowired
	private MarkManagementService markManagementService;
	
	@PutMapping("/")
	@PreAuthorize("hasAnyAuthority('ADMIN','TUTOR')")
	public Map<String,String> studentMarkUpdated(){
		 return markManagementService.studentMarkUpdated();
	}
	
	@GetMapping("/mark")
	@PreAuthorize("hasAnyAuthority('ADMIN','TUTOR','STUDENT')")
	public List<MarkManagement> retriveSingleRecord(@RequestParam Long studentId,@RequestParam Long courseId) {
		return markManagementService.retriveSingleRecord(studentId, courseId);
	}
	

}
