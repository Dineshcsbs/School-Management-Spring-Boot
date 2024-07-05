package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.entities.StudentAnswer;
import com.school.management.service.StudentAnswerService;

@RestController
@RequestMapping("/api/student-answer")
public class StudentAnswerController {

	@Autowired
	private StudentAnswerService studentAnswerService;
	
	@PostMapping("/")
	@PreAuthorize("hasAnyAuthority('STUDENT')")
	public StudentAnswer studentAnswer(@RequestBody StudentAnswer studentAnswer) {
		return this.studentAnswerService.studentAnswer(studentAnswer);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasAnyAuthority('ADMIN','TUTOR')")
	public List<StudentAnswer> retriveStudentAns(@RequestParam(required = false) Long studentId,@RequestParam(required = false) Long courseId){
		return this.studentAnswerService.retriveStudentAns(studentId,courseId);
	}
}
