package com.school.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.Tutor;
import com.school.management.service.TutorService;



@RestController
@RequestMapping("/api/tutor")
public class TutorController {

	@Autowired
	private TutorService tutorService;

	@PostMapping("/")
	public Tutor createRecord(@RequestBody Tutor tutor) {
		return tutorService.createRecord(tutor);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable Long id) {
		return tutorService.deleteByIdRecord(id);
	}
	
	@PutMapping("/update/{id}")
	public Map<String,Object> updateByRecord(@PathVariable Long id,@RequestBody Tutor tutor){
		return tutorService.updateByTutor(id,tutor);
	}	


}
