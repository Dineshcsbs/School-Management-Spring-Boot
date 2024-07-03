package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.StudentDTO;
import com.school.management.entities.Student;
import com.school.management.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PutMapping("/update")
	public Student updateStudent(@RequestBody Student student, @RequestHeader("Authorization") String token) {
//		String token = (request.getHeader("Authorization")).substring(7);

		return studentService.updateStudent(token.substring(7), student);
	}

	@GetMapping("/search")
	public List<StudentDTO> searchData(@RequestParam(defaultValue = "0") int offSet,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String fieldName,
			@RequestParam(defaultValue = "ASC") Direction direction, @RequestParam String searchKeyWord) {
		return studentService.searchData(offSet, pageSize, fieldName, direction, searchKeyWord);
	}

}
