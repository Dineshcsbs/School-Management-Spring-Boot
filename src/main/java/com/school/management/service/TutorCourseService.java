package com.school.management.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.TutorCourse;
import com.school.management.repository.TutorCourseRepository;

@Service
public class TutorCourseService {

	@Autowired
	private TutorCourseRepository tutorCourseRepository;
	public TutorCourse createRecord(final TutorCourse tutorCourse) {
		return tutorCourseRepository.save(tutorCourse);
	}

	//delete a value
	public Map<String,Object> deleteByIdRecord(final Long id){
		final Map<String,Object> responce=new HashMap<>();
		final boolean ifIdExit=tutorCourseRepository.existsById(id);
		if(ifIdExit) {
			tutorCourseRepository.deleteById(id);
			responce.put("Id Successfully Delete ", id);
			return responce;
		}
		responce.put("Id Not Found ", id);
		return responce;			
	}
	
	
}