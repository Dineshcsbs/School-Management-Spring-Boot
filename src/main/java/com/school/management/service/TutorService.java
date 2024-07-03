package com.school.management.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.Tutor;
import com.school.management.repository.TutorRepository;


@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;


	public Tutor createRecord(Tutor tutor) {
		return tutorRepository.save(tutor);
	}
	
	public Map<String, Object> deleteByIdRecord(Long id) {
		Map<String, Object> responce = new HashMap<>();
		boolean ifIdExit = tutorRepository.existsById(id);
		if (ifIdExit) {
			tutorRepository.deleteById(id);
			responce.put("Id Successfully Delete ", id);
			return responce;
		}
		responce.put("Id Not Found ", id);
		return responce;

	}

	public Map<String, Object> updateByTutor(Long id, Tutor tutorDetails) {
		Map<String,Object> responce=new HashMap<>();
		Optional<Tutor> tutorId=tutorRepository.findById(id);
		if(tutorId.isEmpty()) {
			responce.put("Id Not Found ", id);
			return responce;
		}
		Tutor tutor=tutorId.get();
		if(tutor.getAddress()!=null) {
			tutor.setAddress(tutorDetails.getAddress());
		}
		if(tutor.getName()!=null) {
			tutor.setName(tutorDetails.getName());
		}	
		createRecord(tutor);
		responce.put("Successfully update ", id);
		return responce;
	}

}
