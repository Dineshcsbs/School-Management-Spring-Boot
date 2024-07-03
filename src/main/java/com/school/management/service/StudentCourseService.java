package com.school.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.StudentCourse;
import com.school.management.repository.StudentCourseRepository;



@Service
public class StudentCourseService {

	@Autowired
	private StudentCourseRepository studentCourseRepository;
	public StudentCourse createRecord(StudentCourse studentCourse) {
		return studentCourseRepository.save(studentCourse);
	}
	public List<StudentCourse> assignCourseToStudent(Long id) {
		
		return studentCourseRepository.findAllByStudentId(id);
	}

	public Map<String,Object> deleteByStudentCourse(Long studentId, Long courseId) {
        
		Map<String,Object> responce=new HashMap<>();
		StudentCourse student=studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
		if(student.getId()!=null) {
			studentCourseRepository.deleteById(student.getId());
			responce.put("Id Successfully Delete ", courseId);
			return responce;
		}
		responce.put("Id Not Found ", courseId);
		return responce;			
	}
	
}
