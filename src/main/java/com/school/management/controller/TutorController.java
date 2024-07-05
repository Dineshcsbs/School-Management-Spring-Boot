package com.school.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.TutorDto;
import com.school.management.entities.Tutor;
import com.school.management.service.TutorService;



@RestController
@RequestMapping("/api/tutor")
public class TutorController {

	@Autowired
	private TutorService tutorService;

	@PostMapping("/")
	public Tutor createRecord(@RequestBody Tutor tutor) {
		return this.tutorService.createRecord(tutor);
	}
	@GetMapping("/")
	public List<TutorDto> getTutorDetail(@RequestParam String email){
		return tutorService.getTutorDetail(email);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable Long id) {
		return this.tutorService.deleteByIdRecord(id);
	}
	
	@PutMapping("/")
	public Map<String,Object> updateByRecord(@RequestBody Tutor tutor){
		return this.tutorService.updateByTutor(tutor);
	}	


}
