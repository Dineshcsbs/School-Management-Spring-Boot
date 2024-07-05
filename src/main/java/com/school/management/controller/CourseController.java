package com.school.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.Course;
import com.school.management.service.CourseService;


@RestController
@RequestMapping("/api/course")
public class CourseController {


	@Autowired
	private CourseService courseService;
	
	@PostMapping("/")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Course createRecord(@RequestBody Course course) {
		return this.courseService.createRecord(course);
	}
	
	@GetMapping("/{courseId}")
	@PreAuthorize("hasAnyAuthority('ADMIN','TUTOR','STUDENT')")
	public Course getCourseName(@PathVariable Long courseId) {
		return this.courseService.getCourseName(courseId);
	}
	
	
	@DeleteMapping("/{courseId}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Map<String,Object> deleteById(@PathVariable Long courseId){
		return this.courseService.deleteByIdRecord(courseId);
	}
	
}
