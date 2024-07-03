package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.QuestionDTO;
import com.school.management.entities.Question;
import com.school.management.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/create")
	@PreAuthorize("hasAnyAuthority('TUTOR')")
	public Question createQuestion(@RequestBody Question question) {
		return questionService.createQuestion(question);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('TUTOR')")
	public List<QuestionDTO> displayQuestion(@PathVariable Long id){
		return questionService.displayQuestion(id);
	}

	@GetMapping("/page")
	@PreAuthorize("hasAnyAuthority('TUTOR','STUDENT')")
	public List<QuestionDTO> pageConcept(@RequestParam(defaultValue="0") int  pageNo,@RequestParam(defaultValue="6") int pageSize,
			@RequestParam(defaultValue="id")String fieldName,@RequestParam(defaultValue="ASC")Direction direction,
			@RequestParam Long courseId){
		return questionService.pageConcept(pageNo,pageSize,fieldName, direction,courseId);
	}
}
