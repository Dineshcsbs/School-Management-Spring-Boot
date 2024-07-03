package com.school.management.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.Course;
import com.school.management.repository.CourseRepository;



@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public Course createRecord(Course course) {

		return courseRepository.save(course);
	}

	public Course getCourseName(Long id) {
		try {
			return courseRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
		} catch (Exception e) {
			throw new RuntimeException("Course not found with id: " + id);
		}
	}

	public Map<String, Object> deleteByIdRecord(Long id) {
		Map<String, Object> responce = new HashMap<>();
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
			responce.put("Id Successfully Delete ", id);
			return responce;
		}
		responce.put("Id Not Found ", id);
		return responce;

	}

}
