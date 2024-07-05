package com.school.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.management.dto.StudentDTO;
import com.school.management.entities.Student;
import com.school.management.exception.BadRequestServiceAlertException;
import com.school.management.repository.StudentRepository;
import com.school.management.repository.UserRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepository userRepository;

	public Student updateStudent(final String token, final Student studentInfo) {
		if(!jwtService.extractUsername(token).equals(studentInfo.getEmail())) {
			throw new BadRequestServiceAlertException(400,"Email or token invalid");
		}
		
		Optional<Student> student=studentRepository.findByEmail(studentInfo.getEmail());
		if(student.isEmpty()) {
			throw new BadRequestServiceAlertException(400,"Email is not invalid");
		}
		
		Student studentDetail=student.get();
		if(studentInfo.getAddress()!=null) {
			studentDetail.setAddress(studentInfo.getAddress());
		}
		return studentRepository.save(studentDetail);
	}
	
	public List<StudentDTO> searchData(final int offSet,final int pageSize,final String fieldName,final Sort.Direction direction, final String searchKeyWord) {
		final Page<Student> response = studentRepository.searchStudents(searchKeyWord, PageRequest.of(offSet, pageSize, Sort.by(direction, fieldName)));
		final Page<StudentDTO> responseDTO = response.map(this::convertToDTO);
        return responseDTO.getContent();
    }

    public StudentDTO convertToDTO(final Student student) {
        return StudentDTO.builder()
                .name(student.getName())
                .email(student.getEmail())
                .schoolName(student.getSchool().getName())
                .address(student.getAddress())
                .build();
    }

	public String deleteByStudent(String email) {
		
		Optional<Student> student=studentRepository.findByEmail(email);
		if(student.isEmpty()) {
			throw new BadRequestServiceAlertException(404,"email not found");
		}
		Student studentDetails=student.get();
		studentRepository.delete(studentDetails);
		userRepository.delete(studentDetails.getUser());
		return "student successfully delete";
	}
	

}
