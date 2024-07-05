package com.school.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.StudentCourse;
import com.school.management.service.StudentCourseService;



@RestController
@RequestMapping("/api/student-course")
public class StudentCourseController {

	@Autowired
	private StudentCourseService studentCourseService;
	@PostMapping("/")
	@PreAuthorize("hasAnyAuthority('ADMIN','TUTOR','STUDENT')")
	public StudentCourse createRecord(@RequestBody StudentCourse studentCourse) {
		return this.studentCourseService.createRecord(studentCourse);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','TUTOR','STUDENT')")
	public List<StudentCourse> assignCourseToStudent(@PathVariable Long studentId){
		return this.studentCourseService.assignCourseToStudent(studentId);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','TUTOR')")
	public Map<String,Object> deleteByIdRecord(@RequestParam Long studentId,@RequestParam Long courseId){
		return this.studentCourseService.deleteByStudentCourse(studentId,courseId);
	}
}
