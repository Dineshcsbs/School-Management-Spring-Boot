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

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private JwtService jwtService;

	public Student updateStudent(String token, Student studentInfo) {
		if(!jwtService.extractUsername(token).equals(studentInfo.getEmail())) {
			throw new BadRequestServiceAlertException("Email or token invalid");
		}
		
		Optional<Student> student=studentRepository.findByEmail(studentInfo.getEmail());
		if(student.isEmpty()) {
			throw new BadRequestServiceAlertException("Email is not invalid");
		}
		
		Student studentDetail=student.get();
		if(studentInfo.getAddress()!=null) {
			studentDetail.setAddress(studentInfo.getAddress());
		}
		return studentRepository.save(studentDetail);
	}
	
	public List<StudentDTO> searchData(int offSet,int pageSize,String fieldName,Sort.Direction direction, String searchKeyWord) {
        Page<Student> response = studentRepository.searchStudents(searchKeyWord, PageRequest.of(offSet, pageSize, Sort.by(direction, fieldName)));
        Page<StudentDTO> responseDTO = response.map(this::convertToDTO);
        return responseDTO.getContent();
    }

    public StudentDTO convertToDTO(Student student) {
        return StudentDTO.builder()
                .name(student.getName())
                .email(student.getEmail())
                .schoolName(student.getSchool().getName())
                .address(student.getAddress())
                .build();
    }
	

}
