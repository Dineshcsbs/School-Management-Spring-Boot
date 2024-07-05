package com.school.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.dto.TutorDto;
import com.school.management.entities.Tutor;
import com.school.management.entities.TutorCourse;
import com.school.management.exception.BadRequestServiceAlertException;
import com.school.management.repository.TutorRepository;


@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;


	public Tutor createRecord(final Tutor tutor) {
		return tutorRepository.save(tutor);
	}
	
	public Map<String, Object> deleteByIdRecord(final Long id) {
		final Map<String, Object> responce = new HashMap<>();
		final boolean ifIdExit = tutorRepository.existsById(id);
		if (ifIdExit) {
			
			tutorRepository.deleteById(id);
			responce.put("Id Successfully Delete ", id);
			return responce;
		}
		responce.put("Id Not Found ", id);
		return responce;

	}

	public Map<String, Object> updateByTutor(final Tutor tutorDetails) {
		Tutor tutorInfo=tutorRepository.findByEmail(tutorDetails.getEmail());
		if(tutorInfo.getId()==null) {
			throw new BadRequestServiceAlertException(400,"Your is a user");
		}
		Long id=tutorInfo.getId();
		final Map<String,Object> responce=new HashMap<>();
		final Optional<Tutor> tutorId=tutorRepository.findById(id);
		if(tutorId.isEmpty()) {
			responce.put("Id Not Found ", id);
			return responce;
		}
		final Tutor tutor=tutorId.get();
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

	public List<TutorDto> getTutorDetail(final String email) {
		final List<TutorCourse> tutor =tutorRepository.findTutorAndCoursesByEmail(email);
		if(tutor.isEmpty()) {
			throw new BadRequestServiceAlertException(404,"Email is not found");
		}
		List<TutorDto> tutorDetails = tutor.stream()
	            .map(this::convertTutorDto)
	            .collect(Collectors.toList());
		return tutorDetails;
	}
	public TutorDto convertTutorDto(final TutorCourse tutor) {
		return TutorDto.builder().name(tutor.getTutor().getName())
				.address(tutor.getTutor().getAddress())
				.course(tutor.getCourse().getName())
				.schoolName(tutor.getTutor().getSchool().getName())
				.build();
	}

}
