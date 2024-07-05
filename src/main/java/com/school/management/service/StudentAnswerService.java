package com.school.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.StudentAnswer;
import com.school.management.repository.StudentAnswerRepository;


@Service
public class StudentAnswerService {

	@Autowired
	private StudentAnswerRepository studentAnswerRepository;
	
	
	public StudentAnswer studentAnswer(final StudentAnswer studentAnswer) {
		
		return studentAnswerRepository.save(studentAnswer);
	}


	public List<StudentAnswer> retriveStudentAns(final Long studentId, final Long courseId) {
		return studentAnswerRepository.searchStudentAnswer(studentId,courseId);
	}

}
