package com.school.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.TutorCourse;
import com.school.management.service.TutorCourseService;


@RestController
@RequestMapping("/api/tutor-course")
public class TutorCourseController {

	@Autowired
	private TutorCourseService tutorCourseService;
	
	@PostMapping("/")
	@PreAuthorize("hasAnyAuthority('TUTOR','ADMIN')")
	public TutorCourse createrecord(@RequestBody TutorCourse tutorCourse) {
		return tutorCourseService.createRecord(tutorCourse);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Map<String,Object> deleteByIdRecord(@PathVariable Long id){
		return tutorCourseService.deleteByIdRecord(id);
	}

}
